package com.mongoAndSqlNew.service;

import com.mongoAndSqlNew.entity.UserPostgreSql;
import com.mongoAndSqlNew.entity.UserPostgreView;

import java.util.List;

public interface UserPostgreSqlService {
//  UserPostgreSql createUser(UserPostgreSql user);
    String addUser(String name, String email);
    UserPostgreSql getUserById(Long id);
    List<UserPostgreSql> getAllUsers();
    UserPostgreSql updateUser(Long id, UserPostgreSql user);
    void deleteUser(Long id);
    List<UserPostgreView> getAllUsersFromView();
}

