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

    // LAZY 지연 로딩 사용을 위한 지정
    @ManyToOne(fetch = FetchType.LAZY) private Member writer;

    public void changeTitle(String title) {
        this.title = title;
    } // changeTitle() 끝

    public void changeContent(String content) {
        this.content = content;
    }

} // class 끝
