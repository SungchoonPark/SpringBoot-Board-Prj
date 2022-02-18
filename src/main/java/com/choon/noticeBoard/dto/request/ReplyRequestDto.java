package com.choon.noticeBoard.dto.request;

import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Reply.Reply;
import com.choon.noticeBoard.model.User.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDto {
    private Long id;
    private String content;
    private int likes;
    private User user;
    private Board board;

    @Builder
    public Reply toEntity() {
        return Reply.builder()
                .content(content)
                .likes(likes)
                .board(board)
                .user(user)
                .build();
    }

//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setBoard(Board board) {
//        this.board = board;
//    }

}
