package com.snaketype.dto;

import java.util.Map;

public class DashboardResponse {

    private int totalTests;
    private double averageWpm;
    private double bestWpm;
    private double averageAccuracy;
    private double averageConsistency;
    private Map<String, LanguageStats> languageStats;

    public DashboardResponse() {
    }

    public DashboardResponse(
            int totalTests,
            double averageWpm,
            double bestWpm,
            double averageAccuracy,
            double averageConsistency,
            Map<String, LanguageStats> languageStats) {

        this.totalTests = totalTests;
        this.averageWpm = averageWpm;
        this.bestWpm = bestWpm;
        this.averageAccuracy = averageAccuracy;
        this.averageConsistency = averageConsistency;
        this.languageStats = languageStats;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public double getAverageWpm() {
        return averageWpm;
    }

    public double getBestWpm() {
        return bestWpm;
    }

    public double getAverageAccuracy() {
        return averageAccuracy;
    }

    public double getAverageConsistency() {
        return averageConsistency;
    }

    public Map<String, LanguageStats> getLanguageStats() {
        return languageStats;
    }

    public static class LanguageStats {

        private int tests;
        private double averageWpm;
        private double averageAccuracy;

        public LanguageStats(
                int tests,
                double averageWpm,
                double averageAccuracy) {

            this.tests = tests;
            this.averageWpm = averageWpm;
            this.averageAccuracy = averageAccuracy;
        }

        public int getTests() {
            return tests;
        }

        public double getAverageWpm() {
            return averageWpm;
        }

        public double getAverageAccuracy() {
            return averageAccuracy;
        }
    }
}