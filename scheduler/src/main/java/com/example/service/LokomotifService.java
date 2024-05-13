package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Lokomotif;
import com.example.repository.LokomotifRepo;
import com.example.repository.LokomotifSummaryRepo;

@Service
public class LokomotifService {
    @Autowired
    LokomotifRepo lokomotifRepo;

    @Autowired
    LokomotifSummaryRepo lokomotifSummaryRepo;

    public List<Lokomotif> getAll(){
        return lokomotifRepo.findAll();
    }

    public void createLokomotif(){
        List<Lokomotif> lokomotifMongos = new ArrayList<>();
        Random random = new Random();
        String[] lokomotifNames = {"Lokomotif Express", "Lokomotif Cepat", "Lokomotif Cargo"};
        String[] lokomotifStatus = {"Aktif", "Non Aktif", "Maintenance"};
        int[] panjangs = {14,15,16};
        int[] lebars = {2,3};
        int[] tinggis = {3,4};

        Lokomotif lokomotifMongo = new Lokomotif();
        lokomotifMongo.setKodeLokomotif((UUID.randomUUID()).toString());
        lokomotifMongo.setNamaLokomotif(lokomotifNames[random.nextInt(lokomotifNames.length)]);
            Lokomotif.Dimensi dimensi = lokomotifMongo.new Dimensi();
            dimensi.setPanjang(panjangs[random.nextInt(panjangs.length)]);
            dimensi.setLebar(lebars[random.nextInt(lebars.length)]);
            dimensi.setTinggi(tinggis[random.nextInt(tinggis.length)]);
        lokomotifMongo.setDimensiLokomotif(dimensi);
        lokomotifMongo.setStatus(lokomotifStatus[random.nextInt(lokomotifStatus.length)]);
        lokomotifMongo.setWaktu((LocalDateTime.now()).toString());

        lokomotifMongos.add(lokomotifMongo);

        //lokomotifRepo.save(lokomotifMongo);
        System.out.println(lokomotifMongos);
        // callNodeAPISendToKafka(lokomotifMongos);
    }

    public void callNodeAPISendToKafka(List<Lokomotif> lokomotifMongos){
        String apiUrl = "http://localhost:3000/send-lokomotif-to-kafka";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Lokomotif>> httpEntity = new HttpEntity<>(lokomotifMongos, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(apiUrl, httpEntity, String.class);
        // String response = restTemplate.postForObject(apiUrl, httpEntity, String.class);
    }

    @Scheduled(fixedRate = 10000)
    public void generateDummyLokomotif(){
        createLokomotif();
    }
}
