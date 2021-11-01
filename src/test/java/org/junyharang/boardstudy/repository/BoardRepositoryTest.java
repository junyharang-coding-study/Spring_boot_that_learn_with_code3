package org.junyharang.boardstudy.repository;

import org.junit.jupiter.api.Test;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired BoardRepository boardRepository;

    @Test public void 게시글_등록() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .email("junyharang" +i +"@junyharang.com")
                    .build();

            Board board = Board.builder()
                    .title("게시글 테스트" + i)
                    .content("테스트 중 입니다!" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        });

    } // 게시글_등록() 끝

} // class 끝