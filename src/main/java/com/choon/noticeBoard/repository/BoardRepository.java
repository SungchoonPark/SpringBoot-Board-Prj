package com.choon.noticeBoard.repository;

import com.choon.noticeBoard.model.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
