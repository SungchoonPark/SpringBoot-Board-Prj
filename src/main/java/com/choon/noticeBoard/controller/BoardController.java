package com.choon.noticeBoard.controller;

import com.choon.noticeBoard.dto.BoardDto;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("boards", boardService.getBoardList(pageable));
        return "index";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @PostMapping("/post")
    public String post(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detailPost(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("board", boardDto);
        return "detailPost";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("board", boardDto);
        return "editPost";
    }

    @PutMapping("/edit/{id}")
    public String update(@PathVariable Long id, BoardDto boardDto) {
        boardService.update(id, boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
        public String delete(@PathVariable Long id) {
            boardService.deletePost(id);
            return "redirect:/";
    }

}
