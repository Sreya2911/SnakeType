package com.snaketype.service;

import com.snaketype.entity.Attempt;
import com.snaketype.repository.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class HistoryService {

    private final AttemptRepository attemptRepository;

    public HistoryService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public List<Attempt> getHistory(Long userId) {
        return attemptRepository.findAll()
                .stream()
                .filter(attempt ->
                        attempt.getUser().getId().equals(userId))
                .sorted(
                        Comparator.comparing(
                                Attempt::getCompletedAt
                        ).reversed()
                )
                .toList();
    }
}