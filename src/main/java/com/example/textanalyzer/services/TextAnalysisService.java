package com.example.textanalyzer.services;


import com.example.textanalyzer.modals.TextAnalysis;
import com.example.textanalyzer.repositories.TextAnalysisRepository;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class TextAnalysisService {

    private TextAnalysisRepository textAnalysisRepository;

    public TextAnalysisService(TextAnalysisRepository textAnalysisRepository) {
        this.textAnalysisRepository = textAnalysisRepository;
    }

    public String detectLanguage(String text) throws IOException {
        InputStream modelIn = new FileInputStream("src/main/resources/models/langdetect-183.bin");
        LanguageDetectorModel model = new LanguageDetectorModel(modelIn);
        LanguageDetector detector = new LanguageDetectorME(model);
        Language bestLanguage = detector.predictLanguage(text);
        return bestLanguage.getLang();
    }

    public TextAnalysis analyze(String text) throws IOException {
        TextAnalysis textAnalysis = new TextAnalysis();
        textAnalysis.setText(text);
        textAnalysis.setLanguage(detectLanguage(text));
        textAnalysis.setSentiment(analyseSentiment(text));
        textAnalysis.setWordFrequency(analyseWordFrequency(text));
        saveTextAnalysis(textAnalysis);
        return textAnalysis;
    }

    @Cacheable("nlpPipeline")
    public StanfordCoreNLP initializePipeline() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, sentiment");
        return new StanfordCoreNLP(props);
    }

    public String analyseSentiment(String text) {
        Properties properties = this.initializePipeline().getProperties();
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).getFirst();
        return sentence.get(SentimentCoreAnnotations.SentimentClass.class);
    }

    public String analyseWordFrequency(String text) {
        text = text.replaceAll("[^a-zA-Z ]", "");

        String[] words = text.split("\\s+");

        Map<String, Integer> wordFrequency = new java.util.HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        StringBuilder result = new StringBuilder("Word frequency : \n");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            result.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }

        return result.toString();
    }

    public void saveTextAnalysis(TextAnalysis textAnalysis) {
        textAnalysisRepository.save(textAnalysis);
    }

    public List<TextAnalysis> getAllTextAnalysis() {
        return textAnalysisRepository.findAll();
    }

}
