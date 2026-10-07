package com.snaketype.service;

import com.snaketype.dto.DashboardResponse;
import com.snaketype.entity.Attempt;
import com.snaketype.repository.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final AttemptRepository attemptRepository;

    public DashboardService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public DashboardResponse getDashboard(Long userId) {

        List<Attempt> attempts = attemptRepository.findAll()
                .stream()
                .filter(a -> a.getUser().getId().equals(userId))
                .toList();

        if (attempts.isEmpty()) {
            return new DashboardResponse(
                    0,
                    0,
                    0,
                    0,
                    0,
                    new LinkedHashMap<>()
            );
        }

        double averageWpm = attempts.stream()
                .mapToDouble(Attempt::getWpm)
                .average()
                .orElse(0);

        double bestWpm = attempts.stream()
                .mapToDouble(Attempt::getWpm)
                .max()
                .orElse(0);

        double averageAccuracy = attempts.stream()
                .mapToDouble(Attempt::getAccuracy)
                .average()
                .orElse(0);

        double averageConsistency = attempts.stream()
                .mapToDouble(Attempt::getConsistency)
                .average()
                .orElse(0);

        Map<String, DashboardResponse.LanguageStats> languageStats =
                attempts.stream()
                        .collect(Collectors.groupingBy(
                                a -> a.getLanguage().getName(),
                                LinkedHashMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> new DashboardResponse.LanguageStats(
                                                list.size(),
                                                list.stream()
                                                        .mapToDouble(Attempt::getWpm)
                                                        .average()
                                                        .orElse(0),
                                                list.stream()
                                                        .mapToDouble(Attempt::getAccuracy)
                                                        .average()
                                                        .orElse(0)
                                        )
                                )
                        ));

        return new DashboardResponse(
                attempts.size(),
                Math.round(averageWpm * 10) / 10.0,
                Math.round(bestWpm * 10) / 10.0,
                Math.round(averageAccuracy * 10) / 10.0,
                Math.round(averageConsistency * 10) / 10.0,
                languageStats
        );
    }
}