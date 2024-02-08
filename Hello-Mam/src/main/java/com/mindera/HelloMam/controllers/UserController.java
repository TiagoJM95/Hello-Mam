package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.*;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.user.*;
import com.mindera.HelloMam.services.implementations.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mindera.HelloMam.utils.Messages.USER_DELETED;

@Tag(name = "User", description = "The User API")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, StringRedisTemplate redis) {
        this.userService = userServiceImpl;
    }

    @Operation(
            summary = "Get all Users",
            description = "Get all User entries from the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of User entries",
            content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")})
    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(
            summary = "Get a User entry by id",
            description = "Get a User object by specifying its id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User object",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @Operation(
            summary = "Get a User entry by email",
            description = "Get a User object by specifying its email."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User object",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified email was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "email",description = "Provide an email to be searched", required = true)
    @GetMapping("/email/{email}")
    public ResponseEntity<UserGetDto> getUserByEmail(@PathVariable("email") String email) throws EmailNotFoundException {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @Operation(
            summary = "Get a User entry by username",
            description = "Get a User object by specifying its username."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User object",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified username was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "username",description = "Provide an username to be searched", required = true)
    @GetMapping("/username/{username}")
    public ResponseEntity<UserGetDto> getUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Operation(
            summary = "Create a User entry",
            description = "Create a new User object in the database by providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User Created",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "409",
                    description = "Username or Email already exists.",
                    content = {@Content(schema = @Schema())})
    })
    @PostMapping("/")
    public ResponseEntity<UserGetDto> addNewUser(@Valid @RequestBody UserCreateDto userCreateDto) throws DuplicateEmailException, DuplicateUsernameException {
        return new ResponseEntity<>(userService.addNewUser(userCreateDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a User entry by id",
            description = "Update a User object in the database by providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "User Updated",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @PatchMapping("/username/{id}")
    public ResponseEntity<UserGetDto> updateUsername(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateUsernameDto userUpdateDto) throws UserNotFoundException, DuplicateUsernameException {
        return new ResponseEntity<>(userService.updateUsername(id, userUpdateDto), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Update a User entry by id",
            description = "Update a User object in the database by providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "User Updated",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @PatchMapping("/email/{id}")
    public ResponseEntity<UserGetDto> updateEmail(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateEmailDto userUpdateEmailDto) throws UserNotFoundException, DuplicateEmailException {
        return new ResponseEntity<>(userService.updateEmail(id, userUpdateEmailDto), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Update a User entry by id",
            description = "Update a User object in the database by providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "User Updated",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @PatchMapping("/name/{id}")
    public ResponseEntity<UserGetDto> updateName(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateNameDto userUpdateNameDto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateName(id, userUpdateNameDto), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Update a User entry by id",
            description = "Update a User object in the database by providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "User Updated",
                    content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The User object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @PatchMapping("/birthdate/{id}")
    public ResponseEntity<UserGetDto> updateDateOfBirth(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDateOfBirthDto userUpdateDateOfBirthDto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateDateOfBirth(id, userUpdateDateOfBirthDto), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Delete a user",
            description = "Delete a User object from the database by specifying its id."
    )
    @ApiResponse(
            responseCode = "204",
            description = "User Deleted",
            content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")})
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(USER_DELETED, HttpStatus.NO_CONTENT);
    }
}