package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.UserConverter;
import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.*;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.user.*;
import com.mindera.HelloMam.repositories.UserRepository;
import com.mindera.HelloMam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.converters.UserConverter.fromUserCreateDtoToUserEntity;
import static com.mindera.HelloMam.converters.UserConverter.fromUserEntityToUserGetDto;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Cacheable("users")
    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserConverter::fromUserEntityToUserGetDto)
                .toList();
    }


    @Cacheable("users")
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserGetDto getUserById(Long id) throws UserNotFoundException {
        return fromUserEntityToUserGetDto(findById(id));
    }

    public UserGetDto findByEmail(String email) throws EmailNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
    }

    public UserGetDto findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UsernameNotFoundException::new);
    }

    public UserGetDto addNewUser(UserCreateDto userCreateDto) throws MediaTypeNotFoundException {
        User user = fromUserCreateDtoToUserEntity(userCreateDto);
        user.setActive(true);
        return fromUserEntityToUserGetDto(userRepository.save(user));
    }

    public UserGetDto updateUsername(Long userId, UserUpdateUsernameDto userUpdateUsernameDto) throws UserNotFoundException, DuplicateUsernameException {
        User user = findById(userId);
        if(userRepository.findByUsername(userUpdateUsernameDto.username()).isPresent()){
            throw new DuplicateUsernameException();
        }
        user.setUsername(userUpdateUsernameDto.username());
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateEmail(Long userId, UserUpdateEmailDto userUpdateEmailDto) throws UserNotFoundException, DuplicateEmailException {
        User user = findById(userId);
        if(userRepository.findByEmail(userUpdateEmailDto.email()).isPresent()){
            throw new DuplicateEmailException();
        }
        user.setEmail(userUpdateEmailDto.email());
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateName(Long userId, UserUpdateNameDto userUpdateNameDto) throws UserNotFoundException {
        User user = findById(userId);
        user.setName(userUpdateNameDto.name());
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateDateOfBirth(Long userId, UserUpdateDateOfBirthDto userUpdateDateOfBirthDto) throws UserNotFoundException {
        User user = findById(userId);
        user.setDateOfBirth(userUpdateDateOfBirthDto.dateOfBirth());
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateInterests(Long userId, UserUpdateInterestsDto userUpdateInterestsDto) throws UserNotFoundException, MediaTypeNotFoundException {
        User user = findById(userId);
        user.setInterests(UserConverter.fromStringListToEnumList(userUpdateInterestsDto.interests()));
        return fromUserEntityToUserGetDto(user);
    }


    public void deleteUser(Long id) throws UserNotFoundException {
        User user = findById(id);
        user.setActive(false);
        userRepository.save(user);
    }

}
