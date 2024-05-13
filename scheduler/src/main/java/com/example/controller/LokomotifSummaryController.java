package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LokomotifSummary;
import com.example.service.LokomotifSummaryService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/lokomotif-summary")
public class LokomotifSummaryController {
    @Autowired
    LokomotifSummaryService lokomotifSummaryService;

    @PostMapping("/post-report")
    ResponseEntity<LokomotifSummary> postLokomotifSummary(){
        try {
            return ResponseEntity.ok().body(lokomotifSummaryService.createSummaryReport());
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
