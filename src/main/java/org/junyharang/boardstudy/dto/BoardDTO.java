package org.junyharang.boardstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title;
    private String content;
    private String writerEmail;     // 작성자 Email(id)
    private String writerName;      // 작성자 이름

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private int replyCount;         // 해당 게시글 댓글 수

} // class 끝
