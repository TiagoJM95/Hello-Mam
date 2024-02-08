package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.*;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.user.*;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mindera.HelloMam.utils.Messages.USER_DELETED;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, StringRedisTemplate redis) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserGetDto> getUserByEmail(@PathVariable("email") String email) throws EmailNotFoundException {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserGetDto> getUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping("/")
    public ResponseEntity<UserGetDto> addNewUser(@Valid @RequestBody UserCreateDto userCreateDto) throws MediaTypeNotFoundException {
        return new ResponseEntity<>(userService.addNewUser(userCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/username/{id}")
    public ResponseEntity<UserGetDto> updateUsername(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateUsernameDto userUpdateDto) throws UserNotFoundException, DuplicateUsernameException {
        return new ResponseEntity<>(userService.updateUsername(id, userUpdateDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/email/{id}")
    public ResponseEntity<UserGetDto> updateEmail(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateEmailDto userUpdateEmailDto) throws UserNotFoundException, DuplicateEmailException {
        return new ResponseEntity<>(userService.updateEmail(id, userUpdateEmailDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<UserGetDto> updateName(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateNameDto userUpdateNameDto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateName(id, userUpdateNameDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/birthdate/{id}")
    public ResponseEntity<UserGetDto> updateDateOfBirth(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDateOfBirthDto userUpdateDateOfBirthDto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateDateOfBirth(id, userUpdateDateOfBirthDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/interests/{id}")
    public ResponseEntity<UserGetDto> updateInterests(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateInterestsDto userUpdateInterestsDto) throws UserNotFoundException, MediaTypeNotFoundException {
        return new ResponseEntity<>(userService.updateInterests(id, userUpdateInterestsDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(USER_DELETED, HttpStatus.NO_CONTENT);
    }
}