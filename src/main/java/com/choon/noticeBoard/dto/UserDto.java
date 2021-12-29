package com.choon.noticeBoard.dto;

import com.choon.noticeBoard.model.RoleType;
import com.choon.noticeBoard.model.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private RoleType role;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(RoleType.USER)
                .build();
    }

    @Builder
    public UserDto(String username, String password, String email, RoleType role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
