package com.choon.noticeBoard.controller.test;

import com.choon.noticeBoard.service.BoardFileService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestApiController {

    private final BoardFileService boardFileService;


}
