package com.choon.noticeBoard.controller;

import com.choon.noticeBoard.dto.UserDto;
import com.choon.noticeBoard.model.User.User;
import com.choon.noticeBoard.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/user")
    public String userList(Model model) {
        List<UserDto> userDtoList = userService.getUserList();
        model.addAttribute("userList", userDtoList);
        return "user";
    }

}
