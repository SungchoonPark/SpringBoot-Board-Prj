package com.choon.noticeBoard.model.Board;

import com.choon.noticeBoard.model.BaseTimeEntity;
import com.choon.noticeBoard.model.Board.file.BoardFile;
import com.choon.noticeBoard.model.Reply.Reply;
import com.choon.noticeBoard.model.User.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(
            mappedBy = "board",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<BoardFile> boardFiles = new ArrayList<>();

    @Builder
    public Board(String title, String content, int hits, int likes, User user) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //Board에서 파일 처리 위함
    public void addFile(BoardFile boardFile) {
        // 'this' is Board instance
        this.boardFiles.add(boardFile);

        //게시글에 파일이 저장되어있지 않은 경우 (이거 굳이 필요 없을듯?)
//        if(boardFile.getBoard() != this) {
//            //파일 저장
//            boardFile.setBoard(this);
//        }
    }
}
