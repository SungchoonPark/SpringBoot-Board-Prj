package com.choon.noticeBoard.controller.api;

import com.choon.noticeBoard.dto.ResponseDto;
import com.choon.noticeBoard.dto.UserDto;
import com.choon.noticeBoard.model.User.User;
import com.choon.noticeBoard.repository.UserRepository;
import com.choon.noticeBoard.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseDto<Integer> join(UserDto userDto) {
        System.out.println("UserApiController : join() 호출됨");
        userService.joinUser(userDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
