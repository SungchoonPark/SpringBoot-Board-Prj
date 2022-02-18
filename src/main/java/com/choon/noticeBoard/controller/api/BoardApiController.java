package com.choon.noticeBoard.controller.api;

import com.choon.noticeBoard.config.auth.principal.PrincipalDetails;
import com.choon.noticeBoard.dto.ResponseDto;
import com.choon.noticeBoard.dto.request.BoardRequestDto;
import com.choon.noticeBoard.dto.request.ReplyRequestDto;
import com.choon.noticeBoard.model.User.User;
import com.choon.noticeBoard.service.BoardFileService;
import com.choon.noticeBoard.service.BoardService;
import com.choon.noticeBoard.vo.BoardFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final BoardFileService boardFileService;

    // controller에서 board객체를 생성하여 보내주기?
    @PostMapping("/api/board")
    public ResponseDto<Long> post(
            @RequestPart(value = "key") BoardRequestDto boardRequestDto,
            @RequestPart(value = "file", required = false) List<MultipartFile> files,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws Exception {

        for (MultipartFile file : files) {
            System.out.println("fileName : " + file.getOriginalFilename());
            System.out.println("fileContentType : " + file.getContentType());
        }

        boardRequestDto.setUser(principalDetails.getUser());
        Long testNum = boardService.savePost1(boardRequestDto, files);

        System.out.println("savePost return value : "+testNum);

        return new ResponseDto<Long>(HttpStatus.OK.value(), testNum);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id,@RequestBody BoardRequestDto boardRequestDto) {
        boardService.update(id, boardRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> delete(@PathVariable Long id) {
        boardService.deletePost(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{id}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {
        boardService.saveReply(replyRequestDto, principalDetails.getUser(), id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("api/board/{id}/reply")
    public ResponseDto<Integer> replyDelete(@PathVariable Long id) {
        boardService.deleteReply(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
