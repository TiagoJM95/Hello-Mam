package com.mindera.HelloMam.Controller;

import com.mindera.HelloMam.Dto.Create.UserCreateDto;
import com.mindera.HelloMam.Dto.Get.UserGetDto;
import com.mindera.HelloMam.Entity.User;
import com.mindera.HelloMam.Service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = new User().builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .dateOfBirth(userCreateDto.dateOfBirth())
                .build();
        userServiceImpl.create(user);
        return ResponseEntity.ok(userServiceImpl.create(user));
    }
}
