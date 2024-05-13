package com.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lokomotif_summary")
@EntityListeners(AuditingEntityListener.class) // Enable auditing for this entity to use @CreatedDate and @CreatedBy
public class LokomotifSummary {
    @Id
    @Column(name = "kode_report")
    private UUID kodeReport;

    @Column(name = "nama_report")
    private String namaReport;

    @Column(name = "total_aktif")
    private int totalAktif;

    @Column(name = "total_non_aktif")
    private int totalNonAktif;

    @Column(name = "total_maintenance")
    private int totalMaintenance;

    @Column(name = "total_lokomotif")
    private int totalLokomotif;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;
}
