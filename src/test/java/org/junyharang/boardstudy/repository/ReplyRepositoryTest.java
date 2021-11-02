package org.junyharang.boardstudy.repository;

import org.junit.jupiter.api.Test;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Member;
import org.junyharang.boardstudy.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest class ReplyRepositoryTest {

    @Autowired private ReplyRepository replyrepository;

    @Test public void 댓글_등록() {

        IntStream.rangeClosed(1, 300)
                .forEach(i -> {
                    // 1 ~ 101까지의 임의 번호
                    long bno = (long) (Math.random() * 101) + 1;

                    // DB상에 bno값이 2부터 시작하여 1이 임의 번호로 만들어지면 2로 올리기 위해 if문 작성
                    if (bno == 1) { // bno 값이 1이라면?
                        // bno 값에 1을 더한다.
                        bno += 1;
                    } // if문 끝

                    Board board = Board.builder()
                            .bno(bno)
                            .build();

                    Reply reply = Reply.builder()
                            .text("댓글 시험 중 입니다!" + i)
                            .board(board)
                            .replyer("주니")
                            .build();

                    replyrepository.save(reply);
                });
    } // 댓글_등록() 끝

    @Test public void 댓글_조회1() {

        Optional<Reply> result = replyrepository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());

    } // 댓글_조회1() 끝
} // class 끝