package com.snaketype.controller;

import com.snaketype.entity.Language;
import com.snaketype.service.PracticeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final PracticeService practiceService;

    public LanguageController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }

    @GetMapping
    public List<Language> getLanguages() {
        return practiceService.getLanguages();
    }
}