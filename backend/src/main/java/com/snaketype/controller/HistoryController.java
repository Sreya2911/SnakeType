package com.snaketype.controller;

import com.snaketype.entity.Attempt;
import com.snaketype.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/{userId}")
    public List<Attempt> getHistory(
            @PathVariable Long userId) {

        return historyService.getHistory(userId);
    }
}