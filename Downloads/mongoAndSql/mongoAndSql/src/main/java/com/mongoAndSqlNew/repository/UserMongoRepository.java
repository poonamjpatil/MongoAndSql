package com.mongoAndSqlNew.repository;

import com.mongoAndSqlNew.document.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository <UserMongo, String>{
    boolean existsByEmail(String email);
}
