package com.mongoAndSqlNew.controller;

import com.mongoAndSqlNew.document.UserMongo;
import com.mongoAndSqlNew.payload.UserMongoDto;
import com.mongoAndSqlNew.service.DatabaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userMongo")
//@RequiredArgsConstructor
public class UserMongoController {

    private final DatabaseService databaseService;

    public UserMongoController(DatabaseService databaseService)
    {
        this.databaseService = databaseService;
    }

    @PostMapping("/addUserMongo")
    public ResponseEntity<UserMongo> createUserMongo(@RequestBody UserMongo userMongo) {
        UserMongo user = databaseService.createUser(userMongo);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMongoUsers")
    public ResponseEntity<List<UserMongoDto>> getMongoUsers() {
        List<UserMongoDto> allUsers = databaseService.getAllMongoUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserMongoDto> getMongoUserById(@PathVariable String id) {
        UserMongoDto mongoUserById = databaseService.getMongoUserById(id);
        return new ResponseEntity<>(mongoUserById,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMongoDto> updateUserMongo(@PathVariable String id, @RequestBody UserMongoDto updateuserMongoDto) {
        UserMongoDto userMongoDto = databaseService.updateUserMongo(id, updateuserMongoDto);
        return new ResponseEntity<>(userMongoDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMongoUser(@PathVariable String id) {
        databaseService.deleteMongoUser(id);
        return new ResponseEntity<>("Record deleted!!",HttpStatus.OK);
    }
}
