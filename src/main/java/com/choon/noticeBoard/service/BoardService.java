package com.choon.noticeBoard.service;

import com.choon.noticeBoard.dto.BoardDto;
import com.choon.noticeBoard.model.Board.Board;
import com.choon.noticeBoard.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void savePost(BoardDto boardDto) {
        boardRepository.save(boardDto.toEntity());
    }

    @Transactional
    public List<BoardDto> getBoardList(Pageable pageable) {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .hits(board.getHits())
                    .likes(board.getLikes())
                    .createdDate(board.getCreatedDate())
                    .modifiedDate(board.getModifiedDate())
                    .build();
            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.getById(id);

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .hits(board.getHits())
                .likes(board.getLikes())
                .build();

        return boardDto;
    }

    @Transactional
    public void update(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("글 찾기 실패 해당 ID가 존재하지 않습니다.");
        });
        // 처음에 내가 시도했던 방법.
//        board.setTitle(boardDto.getTitle());
//        board.setContent(boardDto.getContent());

        // board Entity 클래스에 update 메소드를 하나 만들어줘서 수정 기능이 되게 한다.
        board.update(boardDto.getTitle(), boardDto.getContent());
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

}
