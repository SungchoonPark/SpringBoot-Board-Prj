package com.choon.noticeBoard.repository;

import com.choon.noticeBoard.model.Reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
