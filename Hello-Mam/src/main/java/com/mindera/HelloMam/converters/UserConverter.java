package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;

import java.util.List;

public class UserConverter {

    public static UserGetDto toUserGetDto(User user) {
        return new UserGetDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getInterests(),
                user.getDateOfBirth()
        );
    }

    public static User toUser(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .interests(userCreateDto.interests())
                .dateOfBirth(userCreateDto.dateOfBirth())
                .build();
    }
}
