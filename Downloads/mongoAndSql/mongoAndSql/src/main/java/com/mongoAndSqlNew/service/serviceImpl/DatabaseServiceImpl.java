package com.mongoAndSqlNew.service.serviceImpl;

import com.mongoAndSqlNew.config.DuplicateUserException;
import com.mongoAndSqlNew.config.UserNotFoundException;
import com.mongoAndSqlNew.document.UserMongo;
import com.mongoAndSqlNew.entity.User;
import com.mongoAndSqlNew.mapper.UserMapper;
import com.mongoAndSqlNew.payload.UserDto;
import com.mongoAndSqlNew.payload.UserMongoDto;
import com.mongoAndSqlNew.repository.UserMongoRepository;
import com.mongoAndSqlNew.repository.UserRepository;
import com.mongoAndSqlNew.service.DatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {


    private UserRepository userRepository;
    private UserMongoRepository userMongoRepository;
    private UserMapper userMapper;

    public DatabaseServiceImpl(UserRepository userRepository, UserMongoRepository userMongoRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMongoRepository = userMongoRepository;
        this.userMapper = userMapper;
    }


    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("User already exists with email: " + user.getEmail());
        }
        return userRepository.save(user);
    }


    public List<UserDto> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        return userMapper.toDto(allUser);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UserDto updatedUserDto) {

        if (updatedUserDto == null) {
            throw new IllegalArgumentException("Updated user data cannot be null");
        }

        //retrieving existing user from db
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserFromDto(updatedUserDto, existingUser);

        User savedUser = userRepository.save(existingUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    //UserMongo
    @Override
    public UserMongo createUser(UserMongo userMongo) {
        if (userMongoRepository.existsByEmail(userMongo.getEmail())) {
            throw new DuplicateUserException("User already exists with email: " + userMongo.getEmail());
        }
        return userMongoRepository.save(userMongo);
    }

    @Override
    public List<UserMongoDto> getAllMongoUsers() {
        List<UserMongo> all = userMongoRepository.findAll();
        return userMapper.toMongoDto(all);
    }

    @Override
    public UserMongoDto getMongoUserById(String id) {
        UserMongo userMongo = userMongoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id: " + id));

        return userMapper.toMongoDto(userMongo);
    }

    @Override
    public UserMongoDto updateUserMongo(String id, UserMongoDto updatedUserMongoDto) {
        if (updatedUserMongoDto == null) {
            throw new IllegalArgumentException("Updated user data cannot be null");
        }
        UserMongo existingUserMongo = userMongoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserMongoFromDto(updatedUserMongoDto, existingUserMongo);

        UserMongo savedUserMongo = userMongoRepository.save(existingUserMongo);
        return userMapper.toMongoDto(savedUserMongo);
    }

    @Override
    public void deleteMongoUser(String id) {
         userMongoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Mongo User not found with ID: " + id));
        userMongoRepository.deleteById(id);
    }
}






