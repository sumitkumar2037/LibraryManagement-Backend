package com.jwt.service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.dto.CredentialsDto;
import com.jwt.dto.SignUpDto;
import com.jwt.dto.UserDto;
import com.jwt.entity.UserEntity;
import com.jwt.exceptions.AppException;
import com.jwt.mapper.UserMapper;
import com.jwt.repo.userRepo;

@Service
public class userService {
    @Autowired
    private userRepo userrepo;

    private final UserMapper userMapper=Mappers.getMapper(UserMapper.class);;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userrepo.findAll();

    }

    public void addUser(UserEntity userEntity) {
        userrepo.save(userEntity);
    }

    public void deleteUser(Long id) {
        userrepo.deleteById(id);
    }

    public UserDto login(CredentialsDto credentialsDto) {
        UserEntity user = userrepo.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<UserEntity> optionalUser = userrepo.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        UserEntity savedUser = userrepo.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLoginDetail(String login) {
    	System.out.println("hitting here ");
        UserEntity user = userrepo.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
}
