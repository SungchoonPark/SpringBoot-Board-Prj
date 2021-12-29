package com.choon.noticeBoard.service;

import com.choon.noticeBoard.dto.UserDto;
import com.choon.noticeBoard.model.User.User;
import com.choon.noticeBoard.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void joinUser(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(userDto.toEntity());
    }

    @Transactional
    public UserDto showUser(long id) {
        User user = userRepository.findById(id).get();
        UserDto userDto = UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return userDto;
    }

    @Transactional
    public List<UserDto> showUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList) {
            UserDto userDto = UserDto.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
            userDtoList.add(userDto);
        }

        return userDtoList;
    }
}
