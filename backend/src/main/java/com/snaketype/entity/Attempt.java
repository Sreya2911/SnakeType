package com.snaketype.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "snippet_id", nullable = false)
    private Snippet snippet;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(nullable = false)
    private String mode;

    @Column(nullable = false)
    private int duration;

    @Column(name = "characters_typed", nullable = false)
    private int charactersTyped;

    @Column(name = "correct_characters", nullable = false)
    private int correctCharacters;

    @Column(name = "incorrect_characters", nullable = false)
    private int incorrectCharacters;

    @Column(nullable = false)
    private double cpm;

    @Column(nullable = false)
    private double wpm;

    @Column(name = "raw_speed", nullable = false)
    private double rawSpeed;

    @Column(nullable = false)
    private double accuracy;

    @Column(nullable = false)
    private double consistency;

    @Column(name = "error_count", nullable = false)
    private int errorCount;

    @Column(nullable = false)
    private int complexity;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt = LocalDateTime.now();

    public Attempt() {
    }

    public Attempt(User user, Snippet snippet, Language language,
                   String mode, int duration, int charactersTyped,
                   int correctCharacters, int incorrectCharacters,
                   double cpm, double wpm, double rawSpeed,
                   double accuracy, double consistency,
                   int errorCount, int complexity) {
        this.user = user;
        this.snippet = snippet;
        this.language = language;
        this.mode = mode;
        this.duration = duration;
        this.charactersTyped = charactersTyped;
        this.correctCharacters = correctCharacters;
        this.incorrectCharacters = incorrectCharacters;
        this.cpm = cpm;
        this.wpm = wpm;
        this.rawSpeed = rawSpeed;
        this.accuracy = accuracy;
        this.consistency = consistency;
        this.errorCount = errorCount;
        this.complexity = complexity;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}