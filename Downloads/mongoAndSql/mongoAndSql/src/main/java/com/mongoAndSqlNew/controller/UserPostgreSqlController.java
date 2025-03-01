package com.mongoAndSqlNew.controller;

import com.mongoAndSqlNew.entity.UserPostgreView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongoAndSqlNew.entity.UserPostgreSql;
import com.mongoAndSqlNew.service.UserPostgreSqlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usersPostgre")
public class UserPostgreSqlController {

    private UserPostgreSqlService userPostgreSqlService;

    public UserPostgreSqlController(UserPostgreSqlService userPostgreSqlService)
    {
        this.userPostgreSqlService = userPostgreSqlService;
    }

//    @PostMapping("/addUserPostgre")
//    public ResponseEntity<UserPostgreSql> createUser(@RequestBody UserPostgreSql user) {
//        return ResponseEntity.ok(userPostgreSqlService.createUser(user));
//    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String email) {
        return userPostgreSqlService.addUser(name, email);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserPostgreSql> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userPostgreSqlService.getUserById(id));
    }

    @GetMapping("/view")
    public List<UserPostgreView> getUsersFromView() {
        return userPostgreSqlService.getAllUsersFromView();
    }

    @GetMapping
    public ResponseEntity<List<UserPostgreSql>> getAllUsers() {
        return ResponseEntity.ok(userPostgreSqlService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPostgreSql> updateUser(@PathVariable Long id, @RequestBody UserPostgreSql user) {
        return ResponseEntity.ok(userPostgreSqlService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userPostgreSqlService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

