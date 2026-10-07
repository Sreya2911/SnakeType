package com.snaketype.controller;

import com.snaketype.dto.AttemptRequest;
import com.snaketype.entity.Attempt;
import com.snaketype.entity.Snippet;
import com.snaketype.service.AttemptService;
import com.snaketype.service.PracticeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    private final PracticeService practiceService;
    private final AttemptService attemptService;

    public PracticeController(
            PracticeService practiceService,
            AttemptService attemptService) {

        this.practiceService = practiceService;
        this.attemptService = attemptService;
    }

    @GetMapping("/snippet")
    public Snippet getSnippet(
            @RequestParam String language,
            @RequestParam(defaultValue = "1") int complexity) {

        return practiceService.getRandomSnippet(language, complexity);
    }

    @PostMapping("/attempt")
    public Attempt saveAttempt(@RequestBody AttemptRequest request) {
        return attemptService.saveAttempt(request);
    }
}