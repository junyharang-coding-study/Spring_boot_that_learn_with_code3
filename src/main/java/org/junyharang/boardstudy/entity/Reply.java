package org.junyharang.boardstudy.entity;

import lombok.*;

import javax.persistence.*;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter
// @ToString 주의
@ToString(exclude = "board")
@Entity public class Reply extends BaseTimeEntity  {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    
    private String text;
    private String replyer;

    // 연관관계 지정
    @ManyToOne private Board board;
    
} // class 끝
