package com.choon.noticeBoard.model.Board.file;

import com.choon.noticeBoard.model.BaseTimeEntity;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BoardFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

//    @Column(nullable = false)
//    private Long boardId;

//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;

    @Column(nullable = false)
    private String origFileName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @Builder
    public BoardFile(String origFileName, String filePath, Long fileSize, Board board) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;

//        if(!board.getBoardFiles().contains(this)) {
//            board.getBoardFiles().add(this);
//        }
    }


}
