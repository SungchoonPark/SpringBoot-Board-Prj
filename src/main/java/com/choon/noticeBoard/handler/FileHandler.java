package com.choon.noticeBoard.handler;

import com.choon.noticeBoard.dto.BoardFileDto;
import com.choon.noticeBoard.dto.request.BoardFileReuqestDto;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Board.file.BoardFile;
import com.choon.noticeBoard.model.User.User;
import com.choon.noticeBoard.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FileHandler {
    private final BoardFileService boardFileService;

    public List<BoardFile> parseFileInfo(List<MultipartFile> multipartFiles, Board board)throws Exception {
        // 반환할 파일 리스트
        List<BoardFile> handledFileList = new ArrayList<>();

        // 전달되어 온 파일이 존재할 경우
        // multipartFiles.isEmpty()를 사용하지 않은 이유는 NullPointerException 에러가 발생할 수 있기에
        // Null 체크를 해주는 Apache Commons 라이브러리인 CollectionUtils.isEmpty(object)를 사용한다.
        if(!CollectionUtils.isEmpty(multipartFiles)) {
            // 파일명을 업로드 한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            // OS 환경에 따라 경로 구분자 차이가 존재하는데 File.separator를 사용하면 os에 맞춰서 바꿔줌
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            // 파일을 저장할 세부 경로 지정
            String path = "images" + File.separator + current_date;
            File file = new File(path);

            // 디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful) {
                    System.out.println("file: was not succeessful");
                }
            }

            // 다중 파일 처리
            for(MultipartFile multipartFile : multipartFiles) {

                // 파일의 확장자 추출
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                // 확장자명이 존재하지 않을 경우 처리 안함
                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                } else { // 확장자가 jpeg, png인 파일들만 받아서 처리리
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if(contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else {
                        break;
                    }
                }

                // 파일명 중복 피하고자 나노초까지 얻어와 지정
                String new_file_name = System.nanoTime() + originalFileExtension;

                // 파일 DTO 생성 -> multipartFile데이터로 requesetDto를 만드는게 맞음
                BoardFileReuqestDto boardFileReuqestDto = BoardFileReuqestDto.builder()
                        .origFileName(multipartFile.getOriginalFilename())
                        .filePath(path + File.separator + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();

                // 파일 DTO를 이용하여 BoardFile 엔티티 생성
                boardFileReuqestDto.setBoard(board);
                BoardFile boardFile = boardFileReuqestDto.toEntity();

                // 생성 후 리스트에 추가
                handledFileList.add(boardFile);
                // 업로드 한 파일 데이터를 지정한 파일에 저장
                file = new File(absolutePath + path + File.separator + new_file_name);
                System.out.println("파일 저장 위치 : "+absolutePath + path + File.separator + new_file_name);
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }

        }
        return handledFileList;
    }
}
