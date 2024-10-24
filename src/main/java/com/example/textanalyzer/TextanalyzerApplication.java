package com.example.textanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TextanalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextanalyzerApplication.class, args);
    }

}
