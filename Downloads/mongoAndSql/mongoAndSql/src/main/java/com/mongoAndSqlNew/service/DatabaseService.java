package com.mongoAndSqlNew.service;
import com.mongoAndSqlNew.document.UserMongo;
import com.mongoAndSqlNew.entity.User;
import com.mongoAndSqlNew.payload.UserDto;
import com.mongoAndSqlNew.payload.UserMongoDto;

import java.util.List;

public interface DatabaseService {

    // SQL User Methods
    User createUser(User user);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto updatedUserDto);
    void deleteUser(Long id);

    // MongoDB User Methods
    UserMongo createUser(UserMongo userMongo);
    List<UserMongoDto> getAllMongoUsers();
    UserMongoDto getMongoUserById(String id);
    UserMongoDto updateUserMongo(String id, UserMongoDto updatedUser);
    void deleteMongoUser(String id);
}

