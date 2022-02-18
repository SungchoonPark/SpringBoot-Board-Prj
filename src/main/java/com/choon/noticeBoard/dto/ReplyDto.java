package com.choon.noticeBoard.dto;

import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Reply.Reply;
import com.choon.noticeBoard.model.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {
    private Long id;
    private String content;
    private int likes;
    private Long userId;
    private Long boardId;


}
