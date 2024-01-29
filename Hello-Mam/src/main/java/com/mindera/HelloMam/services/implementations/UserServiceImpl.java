package com.mindera.HelloMam.services.implementations;

import com.mindera.HelloMam.converters.UserConverter;
import com.mindera.HelloMam.dtos.creates.UserCreateDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.dtos.updates.UserDateOfBirthUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserEmailUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserNameUpdateDto;
import com.mindera.HelloMam.dtos.updates.UserUsernameUpdateDto;
import com.mindera.HelloMam.entities.User;
import com.mindera.HelloMam.exceptions.user_exceptions.*;
import com.mindera.HelloMam.repositories.UserRepository;
import com.mindera.HelloMam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mindera.HelloMam.converters.UserConverter.toUserGetDto;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserConverter::toUserGetDto)
                .toList();
    }
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserGetDto getUserById(Long id) throws UserNotFoundException {
        return toUserGetDto(findById(id));
    }

    public UserGetDto findByEmail(String email) throws EmailNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
    }

    public UserGetDto findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UsernameNotFoundException::new);
    }

    public UserGetDto create(UserCreateDto userCreateDto) {
        User user = UserConverter.toUser(userCreateDto);
        user.setActive(true);
        return toUserGetDto(userRepository.save(user));
    }

    public UserGetDto updateUsername(Long userId, UserUsernameUpdateDto userUsernameUpdateDto) throws UserNotFoundException, DuplicateUsernameException {
        User user = findById(userId);
        if(userRepository.findByUsername(userUsernameUpdateDto.username()).isPresent()){
            throw new DuplicateUsernameException();
        }
        user.setUsername(userUsernameUpdateDto.username());
        return toUserGetDto(user);
    }

    public UserGetDto updateEmail(Long userId, UserEmailUpdateDto userEmailUpdateDto) throws UserNotFoundException, EmailFoundException {
        User user = findById(userId);
        if(userRepository.findByEmail(userEmailUpdateDto.email()).isPresent()){
            throw new EmailFoundException();
        }
        user.setEmail(userEmailUpdateDto.email());
        return toUserGetDto(user);
    }

    public UserGetDto updateName(Long userId, UserNameUpdateDto userNameUpdateDto) throws UserNotFoundException {
        User user = findById(userId);
        user.setName(userNameUpdateDto.name());
        return toUserGetDto(user);
    }

    public UserGetDto updateDateOfBirth(Long userId, UserDateOfBirthUpdateDto userDateOfBirthUpdateDto) throws UserNotFoundException {
        User user = findById(userId);
        user.setDateOfBirth(userDateOfBirthUpdateDto.dateOfBirth());
        return toUserGetDto(user);
    }


    public void deleteById(Long id) throws UserNotFoundException {
        User user = findById(id);
        user.setActive(false);
        userRepository.save(user);
    }
}
