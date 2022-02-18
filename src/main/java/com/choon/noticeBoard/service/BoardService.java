package com.choon.noticeBoard.service;

import com.choon.noticeBoard.dto.request.BoardRequestDto;
import com.choon.noticeBoard.dto.request.ReplyRequestDto;
import com.choon.noticeBoard.dto.response.BoardResponseDto;
import com.choon.noticeBoard.handler.FileHandler;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.model.Board.file.BoardFile;
import com.choon.noticeBoard.model.User.User;
import com.choon.noticeBoard.repository.BoardFileRepository;
import com.choon.noticeBoard.repository.BoardRepository;
import com.choon.noticeBoard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardFileRepository boardFileRepository;
    private final FileHandler fileHandler;

    //파일 없을때
    @Transactional
    public Long savePost(BoardRequestDto boardRequestDto, User user) {
        boardRequestDto.setUser(user);
        return boardRepository.save(boardRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long savePost1(BoardRequestDto boardRequestDto, // user 또한 포함되어 있는상태
                          List<MultipartFile> files
    ) throws Exception {
        // 영속성 관리
        // 파일 처리를 위한 Board 객체 생성
        Board board = boardRequestDto.toEntity();
        List<BoardFile> handledFileList = fileHandler.parseFileInfo(files, board);

        // 파일이 존재할 때에만 처리
        if(!handledFileList.isEmpty()) {
            for(BoardFile boardFile : handledFileList) {
                // 파일을 DB에 저장
                // 이 save할때 board의 정보도 다같이 저장이된다.
                // repository의 save메서드는 saved된 entity를 반환한다.
                BoardFile testBoardFile = boardFileRepository.save(boardFile);

                System.out.println("boardFile Id : "+boardFile.getBoard().getId());

                board.addFile(testBoardFile);
                //board.addFile(boardFileRepository.save(boardFile));
            }
        } else {
            System.out.println("empty file");
        }

        // 여기서 board 정보를 entity에 저장.
        return boardRepository.save(board).getId();
    }


    @Transactional
    public List<BoardResponseDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();

        for(Board board : boardList) {
            boardResponseDtoList.add(new BoardResponseDto(board));
        }

        return boardResponseDtoList;
    }

    @Transactional
    public BoardResponseDto getPost(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패 : 해당되는 id가 존재하지 않습니다.");
        });

        return new BoardResponseDto(board);
    }

    @Transactional
    public void update(Long id, BoardRequestDto boardRequestDto) {
        // 영속화 시켜준다.
        Board board = boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("글 찾기 실패 해당 ID가 존재하지 않습니다.");
        });

        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void saveReply(@RequestBody ReplyRequestDto replyRequestDto, User user, Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 해당 ID가 존재하지 않습니다.");
        });
        replyRequestDto.setUser(user);
        replyRequestDto.setBoard(board);
        replyRepository.save(replyRequestDto.toEntity());
    }

    @Transactional
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }

}
