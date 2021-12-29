package com.choon.noticeBoard.model.Reply;

import com.choon.noticeBoard.model.BaseTimeEntity;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.User.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 300)
    private String reply;

    private int likes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
