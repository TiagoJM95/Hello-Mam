package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;

public class UserConverter {

    public static UserGetDto toUserGetDto(User user) {
        return new UserGetDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getDateOfBirth()
        );
    }

    public static User toUser(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .dateOfBirth(userCreateDto.dateOfBirth())
                .build();
    }
}
