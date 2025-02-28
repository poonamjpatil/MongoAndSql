package com.mongoAndSqlNew.controller;

import com.mongoAndSqlNew.entity.User;
import com.mongoAndSqlNew.mapper.UserMapper;
import com.mongoAndSqlNew.payload.UserDto;
import com.mongoAndSqlNew.service.DatabaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final DatabaseService databaseService;
    private final UserMapper userMapper;

    public UserController(DatabaseService databaseService, UserMapper userMapper)
    {
        this.databaseService = databaseService;
        this.userMapper = userMapper;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = databaseService.createUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> allUsersDto = databaseService.getAllUsers();
        return new ResponseEntity<>(allUsersDto,HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
            UserDto userById = databaseService.getUserById(id);
            UserDto userDto = userMapper.toDto(userById);
            return ResponseEntity.ok(userDto);
        }

    //========
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = databaseService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        databaseService.deleteUser(id);
        return new ResponseEntity<>("Record deleted!!",HttpStatus.OK);
    }
}

