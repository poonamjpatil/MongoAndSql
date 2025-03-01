package com.mongoAndSqlNew.repository;

import com.mongoAndSqlNew.entity.UserPostgreSql;
import com.mongoAndSqlNew.entity.UserPostgreView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface UserPostgreSqlRepository extends JpaRepository<UserPostgreSql, Long> {
        boolean existsByEmail(String email);

    @Procedure(name = "insert_user")
    String insertUser(@Param("p_name") String name, @Param("p_email") String email);

    @Query(value = "SELECT * FROM user_view", nativeQuery = true)
    List<UserPostgreView> findAllFromView();


}


