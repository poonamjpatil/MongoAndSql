package com.mongoAndSqlNew.mapper;

import com.mongoAndSqlNew.document.UserMongo;
import com.mongoAndSqlNew.entity.User;
import com.mongoAndSqlNew.payload.UserDto;
import com.mongoAndSqlNew.payload.UserMongoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


// Enables Spring integration

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convert Entity to DTO
    UserDto toDto(User user);
    UserDto toDto(UserDto userById);
    List<UserDto> toDto(List<User> users); //List Support


    // Convert DTO to Entity
    User toEntity(UserDto userDto);
    List<User> toEntiy(List<UserDto> userDto);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);

    //For UserMongo
    UserMongoDto toMongoDto(UserMongo userMongo);
    List<UserMongoDto> toMongoDto(List<UserMongo> all);

    UserMongo toEntity(UserMongoDto userMongoDto);

    @Mapping(target = "id", ignore = true)
    void updateUserMongoFromDto(UserMongoDto userMongoDto, @MappingTarget UserMongo entity);



}



