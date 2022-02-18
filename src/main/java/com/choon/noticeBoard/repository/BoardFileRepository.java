package com.choon.noticeBoard.repository;

import com.choon.noticeBoard.model.Board.file.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

}
