package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.UserUpdateDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mindera.HelloMam.converters.UserConverter.toUserGetDto;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userServiceImpl.create(userCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserGetDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) throws Exception {
        User user = userServiceImpl.findById(id);
        user.setUsername(userUpdateDto.username());
        return new ResponseEntity<>(toUserGetDto(userServiceImpl.update(user)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception {
        User user = userServiceImpl.findById(id);
        user.setActive(false);
        return new ResponseEntity<>("user deleted", HttpStatus.NO_CONTENT);
    }
}