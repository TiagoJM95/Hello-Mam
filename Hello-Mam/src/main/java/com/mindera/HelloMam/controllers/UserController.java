package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.UserDateOfBirthUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserEmailUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserNameUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserUsernameUpdateDto;
import com.mindera.HelloMam.exceptions.user_exceptions.EmailFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.EmailNotFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.UserNotFoundException;
import com.mindera.HelloMam.exceptions.user_exceptions.UsernameFoundException;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> findUserById(Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping
    public ResponseEntity<UserGetDto> findByEmail(String email) throws EmailNotFoundException {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping
    public ResponseEntity<UserGetDto> findByUsername(String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
    @PostMapping("/")
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.create(userCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UserGetDto> updateUsername(@PathVariable("username") Long id, @Valid @RequestBody UserUsernameUpdateDto userUpdateDto) throws UserNotFoundException, UsernameFoundException {

        return new ResponseEntity<>(userService.updateUsername(id, userUpdateDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UserGetDto> updateEmail(@PathVariable("email") Long id, @Valid @RequestBody UserEmailUpdateDto userEmailUpdateDto) throws UserNotFoundException, EmailFoundException {

        return new ResponseEntity<>(userService.updateEmail(id, userEmailUpdateDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<UserGetDto> updateName(@PathVariable("name") Long id, @Valid @RequestBody UserNameUpdateDto userNameUpdateDto) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updateName(id, userNameUpdateDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{birthDate}")
    public ResponseEntity <UserGetDto> updateDateOfBirth(@PathVariable("birthDate") Long id, @Valid @RequestBody UserDateOfBirthUpdateDto userDateOfBirthUpdateDto) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updateDateOfBirth(id, userDateOfBirthUpdateDto), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>("user deleted", HttpStatus.NO_CONTENT);
    }
}