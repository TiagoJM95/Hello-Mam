package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.IncompatibleTypeException;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.HelloMam.enums.MediaType.getTypeByDescription;

public class UserConverter {

    public static List<MediaType> fromStringListToEnumList(List<String> interests) throws MediaTypeNotFoundException {
        List<MediaType> list = new ArrayList<>();

        for(String interest : interests) {
            if(getTypeByDescription(interest).isEmpty()) {
                throw new MediaTypeNotFoundException();
            }
            list.add(getTypeByDescription(interest).get());
        }

        return list;
    }

    public static List<String> fromEnumListToStringList(List<MediaType> types) {
        List<String> list = new ArrayList<>();

        for(MediaType type : types) {
            list.add(type.getDescription());
        }

        return list;
    }

    public static UserGetDto fromUserEntityToUserGetDto(User user) {
        return new UserGetDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                fromEnumListToStringList(user.getInterests()),
                user.getDateOfBirth()
        );
    }

    public static User fromUserCreateDtoToUserEntity(UserCreateDto userCreateDto) throws MediaTypeNotFoundException {
        return User.builder()
                .username(userCreateDto.username())
                .email(userCreateDto.email())
                .name(userCreateDto.name())
                .interests(fromStringListToEnumList(userCreateDto.interests()))
                .dateOfBirth(userCreateDto.dateOfBirth())
                .build();
    }
}
