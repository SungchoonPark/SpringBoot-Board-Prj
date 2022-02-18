package com.choon.noticeBoard.dto.response;

import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Reply.Reply;
import com.choon.noticeBoard.model.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private int hits;
    private int likes;
    private User user;
    private List<Reply> replyList;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.hits = board.getHits();
        this.likes = board.getLikes();
        this.user = board.getUser();
        this.replyList = board.getReplyList();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }
}
