package com.choon.noticeBoard.controller.api;

import com.choon.noticeBoard.dto.BoardDto;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    private BoardService boardService;

    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

//    @PostMapping("/post")
//    public String post(BoardDto boardDto) {
//        boardService.savePost(boardDto);
//        return "redirect:/";
//    }
}
