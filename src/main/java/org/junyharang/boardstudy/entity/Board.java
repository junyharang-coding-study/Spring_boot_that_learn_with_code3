package org.junyharang.boardstudy.entity;

import lombok.*;

import javax.persistence.*;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter
// @ToString은 항상 exclude (exclude = 제외하다)
@ToString(exclude = "writer")
@Entity public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    @ManyToOne private Member writer;

} // class 끝
