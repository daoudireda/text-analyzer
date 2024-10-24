package com.example.textanalyzer;


import com.example.textanalyzer.modals.TextAnalysis;
import com.example.textanalyzer.repositories.TextAnalysisRepository;
import com.example.textanalyzer.services.TextAnalysisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TextAnalysisServiceTest {


    @InjectMocks
    private TextAnalysisService textAnalysisService;

    @Mock
    private TextAnalysisRepository textAnalysisRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test()
    void analyzeText() throws IOException {
        String text = "Hello World";


        TextAnalysis analyze = textAnalysisService.analyze(text);

        assertEquals(analyze.getText(), text);
        assertEquals("eng", analyze.getLanguage());
        assertEquals("Positive", analyze.getSentiment());
        assertEquals("Word frequency : \nHello : 1\nWorld : 1\n", analyze.getWordFrequency());
        verify(textAnalysisRepository).save(analyze);


    }


}
