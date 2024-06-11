package com.example.user.backend.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.user.backend.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
}

