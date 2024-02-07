package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.HelloMam.enums.MediaType.getTypeByDescription;

public class UserConverter {

    public static UserGetDto fromUserEntityToUserGetDto(User user) {
        return new UserGetDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getDateOfBirth()
        );
    }

    public static User fromUserCreateDtoToUserEntity(UserCreateDto userCreateDto) throws MediaTypeNotFoundException {
        return User.builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .dateOfBirth(userCreateDto.dateOfBirth())
                .build();
    }
}
