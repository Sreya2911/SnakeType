package com.snaketype.service;

import com.snaketype.entity.Language;
import com.snaketype.entity.Snippet;
import com.snaketype.repository.LanguageRepository;
import com.snaketype.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PracticeService {

    private final LanguageRepository languageRepository;
    private final SnippetRepository snippetRepository;
    private final Random random = new Random();

    public PracticeService(LanguageRepository languageRepository,
                           SnippetRepository snippetRepository) {
        this.languageRepository = languageRepository;
        this.snippetRepository = snippetRepository;
    }

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public Snippet getRandomSnippet(String languageName, int complexity) {

        Language language = languageRepository.findByName(languageName)
                .orElseThrow(() -> new RuntimeException("Language not found"));

        List<Snippet> snippets =
                snippetRepository.findByLanguageIdAndComplexity(
                        language.getId(), complexity);

        if (snippets.isEmpty()) {
            snippets = snippetRepository.findByLanguageId(language.getId());
        }

        if (snippets.isEmpty()) {
            throw new RuntimeException("No snippets available");
        }

        return snippets.get(random.nextInt(snippets.size()));
    }
}