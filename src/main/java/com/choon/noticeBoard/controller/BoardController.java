package com.choon.noticeBoard.controller;

import com.choon.noticeBoard.dto.response.BoardResponseDto;
import com.choon.noticeBoard.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<BoardResponseDto> boardsList = boardService.getBoardList();
        model.addAttribute("boards", boardsList);
        return "index";
    }

    @GetMapping("/board/post")
    public String post() {
        return "post";
    }

    @GetMapping("/board/{id}")
    public String detailPost(@PathVariable Long id, Model model) {
        BoardResponseDto boardResponseDto = boardService.getPost(id);
        model.addAttribute("board", boardResponseDto);

        return "detailPost";
    }

    @GetMapping("/board/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        BoardResponseDto boardResponseDto = boardService.getPost(id);
        model.addAttribute("board", boardResponseDto);
        return "editPost";
    }


}
