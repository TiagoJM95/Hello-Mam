package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.UserDateOfBirthUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserEmailUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserNameUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserUsernameUpdateDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.user_exceptions.EmailFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.EmailNotFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.UserNotFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.UsernameFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    User findById(Long id) throws UserNotFoundException;
    UserGetDto findUserById(Long id) throws UserNotFoundException;
    List<UserGetDto> findAll();

    UserGetDto findByEmail(String email) throws EmailNotFoundException;

    UserGetDto findByUsername(String username);
    UserGetDto create(UserCreateDto userCreateDto);

    UserGetDto updateUsername(Long userId, UserUsernameUpdateDto userUsernameUpdateDto) throws UserNotFoundException, UsernameFoundException;

    UserGetDto updateEmail(Long userId, UserEmailUpdateDto userEmailUpdateDto) throws UserNotFoundException, EmailFoundException;

    UserGetDto updateName(Long userId, UserNameUpdateDto userNameUpdateDto) throws UserNotFoundException;

    UserGetDto updateDateOfBirth(Long userId, UserDateOfBirthUpdateDto userDateOfBirthUpdateDto) throws UserNotFoundException;

    void deleteById(Long id) throws UserNotFoundException;
}
