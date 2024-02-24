package com.example.springmongodb.dao;

import com.example.springmongodb.dto.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
