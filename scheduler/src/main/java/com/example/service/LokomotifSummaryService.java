package com.example.service;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Lokomotif;
import com.example.model.LokomotifSummary;
import com.example.repository.LokomotifRepo;
import com.example.repository.LokomotifSummaryRepo;

@Service
public class LokomotifSummaryService {
    @Autowired
    LokomotifRepo lokomotifRepo;

    @Autowired
    LokomotifSummaryRepo lokomotifSummaryRepo;

    @Autowired
    KafkaTemplate<String, Lokomotif> kafkaTemplate;
    
    public LokomotifSummary createSummaryReport(){
        List<Lokomotif> lokomotifs = lokomotifRepo.findAll();
        LokomotifSummary lokomotifSummary = new LokomotifSummary();

        LocalDateTime reportDate = LocalDateTime.now();
        int countAktif = (int)lokomotifs.stream().filter(loko->loko.getStatus().equals("Aktif")).count();
        int countNonAktif = (int)lokomotifs.stream().filter(loko->loko.getStatus().equals("Non Aktif")).count();
        int countMaintenance = (int)lokomotifs.stream().filter(loko->loko.getStatus().equals("Maintenance")).count();

        lokomotifSummary.setKodeReport(UUID.randomUUID());
        lokomotifSummary.setNamaReport("Report-Lokomotif-"+reportDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        lokomotifSummary.setTotalAktif(countAktif);
        lokomotifSummary.setTotalNonAktif(countNonAktif);
        lokomotifSummary.setTotalMaintenance(countMaintenance);
        lokomotifSummary.setTotalLokomotif(lokomotifs.size()); 
        lokomotifSummary.setCreatedBy("Rheza");
        lokomotifSummary.setCreatedAt(LocalDateTime.now());

        lokomotifSummaryRepo.save(lokomotifSummary);

        return lokomotifSummary;
    }
}