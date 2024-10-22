package com.example.textanalyzer.controllers;

import com.example.textanalyzer.modals.TextAnalysis;
import com.example.textanalyzer.services.TextAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/textAnalysis")
public class TextAnalysisController {
    private TextAnalysisService textAnalysisService;

    public TextAnalysisController(TextAnalysisService textAnalysisService) {
        this.textAnalysisService = textAnalysisService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<TextAnalysis> analyzeText(@RequestParam String textAnalysis) throws IOException {
        TextAnalysis analyze = textAnalysisService.analyze(textAnalysis);
        return ResponseEntity.ok(analyze);
    }

    @GetMapping("/getAllResults")
    public ResponseEntity<List<TextAnalysis>> getAllResults() {
        List<TextAnalysis> allTextAnalysis = textAnalysisService.getAllTextAnalysis();
        return ResponseEntity.ok(allTextAnalysis);
    }


}
