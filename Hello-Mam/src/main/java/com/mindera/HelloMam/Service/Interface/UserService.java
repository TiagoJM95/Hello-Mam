package com.mindera.HelloMam.Service.Interface;

import com.mindera.HelloMam.Dto.Get.UserGetDto;
import com.mindera.HelloMam.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User create(User user);

    User findById(Long id);

    List<UserGetDto> findAll();

    User findByEmail(String email);

    User findByUsername(String username);

    User update(User user);

    void deleteById(Long id);
}
