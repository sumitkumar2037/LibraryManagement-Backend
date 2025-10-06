package com.jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jwt.dto.SignUpDto;
import com.jwt.dto.UserDto;
import com.jwt.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDto toUserDto(UserEntity user);
    @Mapping(target = "password", ignore = true)
    public UserEntity signUpToUser(SignUpDto userDto);

    public UserEntity toUser(UserDto userDto);

}