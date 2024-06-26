package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LokomotifSummary;

@Repository
public interface LokomotifSummaryRepo extends JpaRepository<LokomotifSummary, UUID>{
    
}
