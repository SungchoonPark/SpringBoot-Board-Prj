package com.choon.noticeBoard.dto.request;

import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Board.file.BoardFile;
import com.choon.noticeBoard.model.User.User;
import lombok.*;

@Getter
@NoArgsConstructor
public class BoardFileReuqestDto {
    private String origFileName;
    private String filePath;
    private Long fileSize;
    private Board board;

    @Builder
    public BoardFileReuqestDto(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public BoardFile toEntity() {
        return BoardFile.builder()
                .origFileName(origFileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .board(board)
                .build();
    }
}
