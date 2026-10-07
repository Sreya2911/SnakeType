package com.snaketype.service;

import com.snaketype.entity.Snippet;
import com.snaketype.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AdaptiveService {

    private final SnippetRepository snippetRepository;
    private final Random random = new Random();

    public AdaptiveService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public Snippet chooseSnippet(Long languageId, double accuracy, double wpm) {

        int complexity;

        if (accuracy >= 95 && wpm >= 50) {
            complexity = 5;
        } else if (accuracy >= 90 && wpm >= 40) {
            complexity = 4;
        } else if (accuracy >= 85 && wpm >= 30) {
            complexity = 3;
        } else if (accuracy >= 75) {
            complexity = 2;
        } else {
            complexity = 1;
        }

        List<Snippet> snippets =
                snippetRepository.findByLanguageIdAndComplexity(
                        languageId, complexity);

        if (snippets.isEmpty()) {
            snippets = snippetRepository.findByLanguageId(languageId);
        }

        if (snippets.isEmpty()) {
            throw new RuntimeException("No snippets available");
        }

        return snippets.get(random.nextInt(snippets.size()));
    }
}