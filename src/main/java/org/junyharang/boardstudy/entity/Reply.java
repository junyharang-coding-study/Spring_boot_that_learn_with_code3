package org.junyharang.boardstudy.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @ToString
@Entity public class Reply {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    
    private String text;
    private String replyer;
    
    // Board와의 연관관계 아직 미설정
    
} // class 끝
