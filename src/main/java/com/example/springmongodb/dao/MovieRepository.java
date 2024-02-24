package com.example.springmongodb.dao;

import com.example.springmongodb.dto.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
    public Movie findByTitle(String title);
}
