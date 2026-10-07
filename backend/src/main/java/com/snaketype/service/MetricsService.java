package com.snaketype.service;

import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    public double calculateCpm(int correctCharacters, int durationSeconds) {
        if (durationSeconds <= 0) return 0;
        return (correctCharacters * 60.0) / durationSeconds;
    }

    public double calculateWpm(int correctCharacters, int durationSeconds) {
        if (durationSeconds <= 0) return 0;
        return calculateCpm(correctCharacters, durationSeconds) / 5.0;
    }

    public double calculateRawSpeed(int charactersTyped, int durationSeconds) {
        if (durationSeconds <= 0) return 0;
        return (charactersTyped * 60.0) / durationSeconds / 5.0;
    }

    public double calculateAccuracy(int correctCharacters, int charactersTyped) {
        if (charactersTyped <= 0) return 0;
        return (correctCharacters * 100.0) / charactersTyped;
    }

    public double calculateConsistency(double accuracy, double wpm) {
        double score = (accuracy * 0.7) + (Math.min(wpm, 100) * 0.3);
        return Math.min(score, 100);
    }
}