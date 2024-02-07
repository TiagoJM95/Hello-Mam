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

    public List<UserGetDto> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .map(UserConverter::fromUserEntityToUserGetDto)
                .toList();
    }

    public User findById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(!user.isActive()){
            throw new UserNotFoundException();
        }
        return user;
    }

    public UserGetDto getUserById(Long id) throws UserNotFoundException {
        return fromUserEntityToUserGetDto(findById(id));
    }

    public UserGetDto findByEmail(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
        if (!user.isActive()) {
            throw new EmailNotFoundException();
        }
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto findByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UsernameNotFoundException::new);
        if (!user.isActive()) {
            throw new UsernameNotFoundException();
        }
        return fromUserEntityToUserGetDto(user);
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
        userRepository.save(user);
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateEmail(Long userId, UserUpdateEmailDto userUpdateEmailDto) throws UserNotFoundException, DuplicateEmailException {
        User user = findById(userId);
        if(userRepository.findByEmail(userUpdateEmailDto.email()).isPresent()){
            throw new DuplicateEmailException();
        }
        user.setEmail(userUpdateEmailDto.email());
        userRepository.save(user);
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateName(Long userId, UserUpdateNameDto userUpdateNameDto) throws UserNotFoundException {
        User user = findById(userId);
        user.setName(userUpdateNameDto.name());
        userRepository.save(user);
        return fromUserEntityToUserGetDto(user);
    }

    public UserGetDto updateDateOfBirth(Long userId, UserUpdateDateOfBirthDto userUpdateDateOfBirthDto) throws UserNotFoundException {
        User user = findById(userId);
        user.setDateOfBirth(userUpdateDateOfBirthDto.dateOfBirth());
        userRepository.save(user);
        return fromUserEntityToUserGetDto(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = findById(id);
        user.setActive(false);
        userRepository.save(user);
    }
}