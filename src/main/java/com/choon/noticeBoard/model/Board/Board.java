package com.choon.noticeBoard.model.Board;

import com.choon.noticeBoard.model.BaseTimeEntity;
import com.choon.noticeBoard.model.Reply.Reply;
import com.choon.noticeBoard.model.User.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    private int hits;

    private int likes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade = CascadeType.REMOVE) // orpahnRemoval = true
    private List<Reply> replyList;

    @Builder
    public Board(/*Long id, */String title, String content, int hits, int likes) {
//        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
