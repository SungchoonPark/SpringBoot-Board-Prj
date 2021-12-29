package com.choon.noticeBoard.dto;

import com.choon.noticeBoard.model.Board.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private int hits;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .hits(hits)
                .likes(likes)
                .build();
    }

    @Builder
    public BoardDto(Long id, String title, String content, int hits, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @Builder
    public BoardDto(Long id, String title, String content, int hits, int likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
    }

    @Builder
    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
        this.hits = 0;
        this.likes = 0;
    }
}
