package com.snaketype.service;

import com.snaketype.dto.AttemptRequest;
import com.snaketype.entity.Attempt;
import com.snaketype.entity.Language;
import com.snaketype.entity.Snippet;
import com.snaketype.entity.User;
import com.snaketype.repository.AttemptRepository;
import com.snaketype.repository.LanguageRepository;
import com.snaketype.repository.SnippetRepository;
import com.snaketype.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final SnippetRepository snippetRepository;
    private final LanguageRepository languageRepository;

    public AttemptService(
            AttemptRepository attemptRepository,
            UserRepository userRepository,
            SnippetRepository snippetRepository,
            LanguageRepository languageRepository) {

        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
        this.snippetRepository = snippetRepository;
        this.languageRepository = languageRepository;
    }

    public Attempt saveAttempt(AttemptRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Snippet snippet = snippetRepository.findById(request.getSnippetId())
                .orElseThrow(() -> new RuntimeException("Snippet not found"));

        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new RuntimeException("Language not found"));

        Attempt attempt = new Attempt(
                user,
                snippet,
                language,
                request.getMode(),
                request.getDuration(),
                request.getCharactersTyped(),
                request.getCorrectCharacters(),
                request.getIncorrectCharacters(),
                request.getCpm(),
                request.getWpm(),
                request.getRawSpeed(),
                request.getAccuracy(),
                request.getConsistency(),
                request.getErrorCount(),
                request.getComplexity()
        );

        return attemptRepository.save(attempt);
    }
}