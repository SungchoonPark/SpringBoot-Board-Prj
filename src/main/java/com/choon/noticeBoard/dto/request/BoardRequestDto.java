package com.choon.noticeBoard.dto.request;

import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Board.file.BoardFile;
import com.choon.noticeBoard.model.Reply.Reply;
import com.choon.noticeBoard.model.User.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private Long id;
    private String title;
    private String content;
    private int hits;
    private int likes;
    private User user;

    @Builder
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .hits(0)
                .likes(0)
                .user(user)
                .build();
    }

    public void setUser(User user) {
        this.user = user;
    }

}
