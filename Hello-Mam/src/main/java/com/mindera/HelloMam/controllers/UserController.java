package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.*;
import com.mindera.HelloMam.exceptions.user_exceptions.*;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> findUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserGetDto> findByEmail(@PathVariable("email") String email) throws EmailNotFoundException {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserGetDto> findByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
    @PostMapping("/")
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.create(userCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/username/{id}")
    public ResponseEntity<UserGetDto> updateUsername(@PathVariable("id") Long id, @Valid @RequestBody UserUsernameUpdateDto userUpdateDto) throws UserNotFoundException, DuplicateUsernameException {

        return new ResponseEntity<>(userService.updateUsername(id, userUpdateDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/email/{id}")
    public ResponseEntity<UserGetDto> updateEmail(@PathVariable("id") Long id, @Valid @RequestBody UserEmailUpdateDto userEmailUpdateDto) throws UserNotFoundException, DuplicateEmailException {

        return new ResponseEntity<>(userService.updateEmail(id, userEmailUpdateDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<UserGetDto> updateName(@PathVariable("id") Long id, @Valid @RequestBody UserNameUpdateDto userNameUpdateDto) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updateName(id, userNameUpdateDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/birthdate/{id}")
    public ResponseEntity<UserGetDto> updateDateOfBirth(@PathVariable("id") Long id, @Valid @RequestBody UserDateOfBirthUpdateDto userDateOfBirthUpdateDto) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updateDateOfBirth(id, userDateOfBirthUpdateDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/interests/{id}")
    public ResponseEntity<UserGetDto> updateInterests(@PathVariable("id") Long id, @Valid @RequestBody UserInterestsUpdateDto userInterestsUpdateDto) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updateInterests(id, userInterestsUpdateDto), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>("user deleted", HttpStatus.NO_CONTENT);
    }
}