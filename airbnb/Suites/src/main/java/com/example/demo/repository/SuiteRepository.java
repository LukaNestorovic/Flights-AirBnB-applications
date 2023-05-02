package com.example.demo.repository;

import com.example.demo.model.Suite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SuiteRepository extends MongoRepository<Suite, String> {
    Suite findOneById(String id);

    List<Suite> findAllByLocation(String location);
}

