package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.*;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.user.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(Long id) throws UserNotFoundException;
    UserGetDto getUserById(Long id) throws UserNotFoundException;
    List<UserGetDto> getAllUsers();
    UserGetDto findByEmail(String email) throws EmailNotFoundException;
    UserGetDto findByUsername(String username) throws UsernameNotFoundException;
    UserGetDto addNewUser(UserCreateDto userCreateDto) throws DuplicateUsernameException, DuplicateEmailException;
    UserGetDto updateUsername(Long userId, UserUpdateUsernameDto userUpdateUsernameDto) throws UserNotFoundException, DuplicateUsernameException;
    UserGetDto updateEmail(Long userId, UserUpdateEmailDto userUpdateEmailDto) throws UserNotFoundException, DuplicateEmailException;
    UserGetDto updateName(Long userId, UserUpdateNameDto userUpdateNameDto) throws UserNotFoundException;
    UserGetDto updateDateOfBirth(Long userId, UserUpdateDateOfBirthDto userUpdateDateOfBirthDto) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
}