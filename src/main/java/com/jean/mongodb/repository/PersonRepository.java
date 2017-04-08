package com.jean.mongodb.repository;

import com.jean.mongodb.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
