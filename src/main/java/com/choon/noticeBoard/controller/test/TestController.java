package com.choon.noticeBoard.controller.test;

import com.choon.noticeBoard.dto.response.BoardResponseDto;
import com.choon.noticeBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final BoardService boardService;

    @GetMapping("/test/file")
    public String uploadFile() {

        return "fileForm";
    }



}
