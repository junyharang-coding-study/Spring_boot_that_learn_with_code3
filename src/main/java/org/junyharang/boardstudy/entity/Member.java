package org.junyharang.boardstudy.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @ToString
@Entity public class Member extends BaseTimeEntity  {

    @Id
    private String email;

    private String password;
    private String name;

} // class 끝
