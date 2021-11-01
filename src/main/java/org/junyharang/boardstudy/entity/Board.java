package org.junyharang.boardstudy.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @ToString
@Entity public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    // 작성자는 아직 처리하지 않음

} // class 끝
