package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.media_exceptions.IncompatibleTypeException;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static List<MediaType> fromStringListToEnumList(List<String> interests) throws IncompatibleTypeException {
        List<MediaType> list = new ArrayList<>();
        for(String interest : interests) {
            if(MediaType.getTypeByDescription(interest).isEmpty()) {
                throw new IncompatibleTypeException();
            }
            list.add(MediaType.getTypeByDescription(interest).get());
        }
        return list;
    }

    public static List<String> fromEnumToList(List<MediaType> types) {
        List<String> list = new ArrayList<>();
        for(MediaType type : types) {
            list.add(type.getDescription());
        }
        return list;
    }

    public static UserGetDto toUserGetDto(User user) {
        return new UserGetDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                fromEnumToList(user.getInterests()),
                user.getDateOfBirth()
        );
    }

    public static User toUser(UserCreateDto userCreateDto) throws IncompatibleTypeException {
        return User.builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .interests(fromStringListToEnumList(userCreateDto.interests()))
                .dateOfBirth(userCreateDto.dateOfBirth())
                .build();
    }
}
