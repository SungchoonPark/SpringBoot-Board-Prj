package com.choon.noticeBoard.service;

import com.choon.noticeBoard.repository.ReplyRepository;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }
}
