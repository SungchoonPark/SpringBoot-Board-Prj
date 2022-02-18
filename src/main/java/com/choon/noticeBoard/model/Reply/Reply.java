package com.choon.noticeBoard.model.Reply;

import com.choon.noticeBoard.model.BaseTimeEntity;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.User.User;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 300)
    private String content;

    private int likes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @Builder
    public Reply(String content, int likes, User user, Board board) {
        this.content = content;
        this.likes = likes;
        this.user = user;
        this.board = board;
    }

    @Builder
    public Reply(String content, int likes) {
        this.content = content;
        this.likes = likes;
    }

}
