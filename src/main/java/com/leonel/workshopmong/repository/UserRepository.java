package com.leonel.workshopmong.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leonel.workshopmong.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
