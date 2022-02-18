package com.choon.noticeBoard.dto;

import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Board.file.BoardFile;
import com.choon.noticeBoard.model.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class BoardFileDto {
    private String origiFileName;
    private String filePath;
    private Long fileSize;
    private User user;
    private Board board;

    @Builder
    public BoardFileDto(String origiFileName, String filePath, Long fileSize, User user, Board board) {
        this.origiFileName = origiFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.user = user;
        this.board = board;
    }
}
