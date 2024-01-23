package com.mindera.HelloMam.Service.Implementation;

import com.mindera.HelloMam.Dto.Get.UserGetDto;
import com.mindera.HelloMam.Entity.User;
import com.mindera.HelloMam.Repository.UserRepository;
import com.mindera.HelloMam.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<UserGetDto> findAll() {
        return userRepository.findAll().stream().map(user -> new UserGetDto(user.getUsername())).toList();
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
