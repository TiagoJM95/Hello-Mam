package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserGetDto create(UserCreateDto userCreateDto);

    User findById(Long id);

    List<UserGetDto> findAll();

    User findByEmail(String email);

    User findByUsername(String username);

    User update(User user);

    void deleteById(Long id);
}
