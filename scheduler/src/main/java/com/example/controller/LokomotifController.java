package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Lokomotif;
import com.example.service.LokomotifService;

@RestController
@RequestMapping("/lokomotif")
public class LokomotifController {
    @Autowired
    LokomotifService lokomotifService;

    @GetMapping("/get-all")
    ResponseEntity<List<Lokomotif>> findAll(){
        try {
            List<Lokomotif> lokomotifs = lokomotifService.getAll(); 
            return ResponseEntity.ok().body(lokomotifs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }
}
