package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Lokomotif;

@Repository
public interface LokomotifRepo extends MongoRepository<Lokomotif, String>{
    // @Query(nativeQuery = true, value = "SELECT * FROM lokomotif WHERE ")  
    // int countByStatus(@Param("status") String status);
}
