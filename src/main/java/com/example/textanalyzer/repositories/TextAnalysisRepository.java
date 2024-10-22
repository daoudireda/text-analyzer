package com.example.textanalyzer.repositories;

import com.example.textanalyzer.modals.TextAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextAnalysisRepository extends MongoRepository<TextAnalysis, String> {
}
