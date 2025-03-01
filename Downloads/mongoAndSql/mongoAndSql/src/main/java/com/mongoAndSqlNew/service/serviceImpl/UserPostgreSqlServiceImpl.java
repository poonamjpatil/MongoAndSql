package com.mongoAndSqlNew.service.serviceImpl;
import com.mongoAndSqlNew.config.DuplicateUserException;
import com.mongoAndSqlNew.entity.UserPostgreSql;
import com.mongoAndSqlNew.entity.UserPostgreView;
import com.mongoAndSqlNew.repository.UserPostgreSqlRepository;
import com.mongoAndSqlNew.service.UserPostgreSqlService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPostgreSqlServiceImpl implements UserPostgreSqlService {

    private UserPostgreSqlRepository userPostgreSqlRepository;

    public UserPostgreSqlServiceImpl(UserPostgreSqlRepository userPostgreSqlRepository)
    {
        this.userPostgreSqlRepository = userPostgreSqlRepository;
    }

    @Override
    @Transactional
    public String addUser(String name, String email) {
        if(userPostgreSqlRepository.existsByEmail(email))
        {
            throw new DuplicateUserException("User already exists with email: "+email);
        }
        return userPostgreSqlRepository.insertUser(name, email);
    }

//    @Override
//    public UserPostgreSql createUser(UserPostgreSql user) {
//       if (userPostgreSqlRepository.existsByEmail(user.getEmail()))
//       {
//           throw new DuplicateUserException("User already exists with email: "+ user.getEmail());
//       }
//        return userPostgreSqlRepository.save(user);
//    }


    @Override
    public UserPostgreSql getUserById(Long id) {
        Optional<UserPostgreSql> user = userPostgreSqlRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    //-->Fetch users from view
    public List<UserPostgreView> getAllUsersFromView() {
        return userPostgreSqlRepository.findAllFromView();
    }



    @Override
    public List<UserPostgreSql> getAllUsers() {
        return userPostgreSqlRepository.findAll();
    }

    @Override
    public UserPostgreSql updateUser(Long id, UserPostgreSql updatedUser) {
        if(updatedUser == null){
            throw new IllegalArgumentException("Updated user data cannot be null");
        }
        userPostgreSqlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserPostgreSql existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        return userPostgreSqlRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userPostgreSqlRepository.deleteById(id);
    }

}

//@Override
//    public List<UserPostgreSql> getUsersByEmail(String email) {
//            return userPostgreSqlRepository.findUsersByEmail(email);
//        }
//    }

