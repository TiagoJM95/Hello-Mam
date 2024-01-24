package com.mindera.HelloMam.Controller;

import com.mindera.HelloMam.Dto.Create.UserCreateDto;
import com.mindera.HelloMam.Dto.Get.UserGetDto;
import com.mindera.HelloMam.Entity.User;
import com.mindera.HelloMam.Service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getUser() {
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) {
        User user = new User().builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .dateOfBirth(userCreateDto.dateOfBirth())
                .active(true)
                .build();
        userServiceImpl.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserCreateDto userCreateDto) {
        User user = userServiceImpl.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setUsername(userCreateDto.username());
        user.setEmail(userCreateDto.email());
        user.setName(userCreateDto.name());
        user.setDateOfBirth(userCreateDto.dateOfBirth());
        userServiceImpl.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userServiceImpl.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setActive(false);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
