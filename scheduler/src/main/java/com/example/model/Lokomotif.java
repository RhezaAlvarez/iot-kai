package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

// import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "lokomotifs")
public class Lokomotif {
    @Id
    @Field("kode_lokomotif")
    private String kodeLokomotif;

    @Field("nama_lokomotif")
    private String namaLokomotif;

    @Field("dimensi_lokomotif")
    private Dimensi dimensiLokomotif;

    @Field("status_lokomotif")
    private String status;

    @Field("waktu_lokomotif")
    private String waktu;

    @Data
    public class Dimensi{
        private int panjang;
        private int lebar;
        private int tinggi;
    }
}
