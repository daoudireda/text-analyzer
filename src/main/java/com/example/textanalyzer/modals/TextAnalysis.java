package com.example.textanalyzer.modals;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "textanalysis")
public class TextAnalysis {
    @Id
    private String id;
    private String text;
    private String language;
    private String sentiment;
    private String wordFrequency;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getWordFrequency() {
        return wordFrequency;
    }

    public void setWordFrequency(String wordFrequency) {
        this.wordFrequency = wordFrequency;
    }
}
