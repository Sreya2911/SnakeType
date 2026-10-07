package com.snaketype.dto;

public class AttemptRequest {

    private Long userId;
    private Long snippetId;
    private Long languageId;
    private String mode;
    private int duration;
    private int charactersTyped;
    private int correctCharacters;
    private int incorrectCharacters;
    private double cpm;
    private double wpm;
    private double rawSpeed;
    private double accuracy;
    private double consistency;
    private int errorCount;
    private int complexity;

    public AttemptRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSnippetId() {
        return snippetId;
    }

    public void setSnippetId(Long snippetId) {
        this.snippetId = snippetId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCharactersTyped() {
        return charactersTyped;
    }

    public void setCharactersTyped(int charactersTyped) {
        this.charactersTyped = charactersTyped;
    }

    public int getCorrectCharacters() {
        return correctCharacters;
    }

    public void setCorrectCharacters(int correctCharacters) {
        this.correctCharacters = correctCharacters;
    }

    public int getIncorrectCharacters() {
        return incorrectCharacters;
    }

    public void setIncorrectCharacters(int incorrectCharacters) {
        this.incorrectCharacters = incorrectCharacters;
    }

    public double getCpm() {
        return cpm;
    }

    public void setCpm(double cpm) {
        this.cpm = cpm;
    }

    public double getWpm() {
        return wpm;
    }

    public void setWpm(double wpm) {
        this.wpm = wpm;
    }

    public double getRawSpeed() {
        return rawSpeed;
    }

    public void setRawSpeed(double rawSpeed) {
        this.rawSpeed = rawSpeed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getConsistency() {
        return consistency;
    }

    public void setConsistency(double consistency) {
        this.consistency = consistency;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
}