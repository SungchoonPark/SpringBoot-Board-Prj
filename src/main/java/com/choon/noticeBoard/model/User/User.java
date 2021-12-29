package com.choon.noticeBoard.model.User;

import com.choon.noticeBoard.model.BaseTimeEntity;
import com.choon.noticeBoard.model.RoleType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public String getRoleKey() {
        return this.role.getKey();
    }

    @Builder
    public User(String username, String password, String email, RoleType role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
