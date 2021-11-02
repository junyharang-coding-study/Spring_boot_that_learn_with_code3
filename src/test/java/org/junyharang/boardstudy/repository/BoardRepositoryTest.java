package org.junyharang.boardstudy.repository;

import org.junit.jupiter.api.Test;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    @Transactional @Test public void 게시글_조회1() {

        // DB에 존재하는 번호
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());

    } // 게시글_조회1() 끝

    @Test public void testReadWithWriter() {

        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("-----------------------------------");
        System.out.println(Arrays.toString(arr));

    } // testReadWithWriter() 끝
    
    @Test public void 게시판_댓글_조회() {
        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        } // for문 끝
    } // 게시판_댓글_조회() 끝

    @Test public void 댓글_수_조회() {

        Pageable pageable = PageRequest.of(0, 20, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;

            System.out.println(Arrays.toString(arr));
        });
    } // 댓글_수_조회() 끝
    
    @Test public void 게시글_조회2() {
        Object result = boardRepository.getBoardByBno(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    } // 게시글_조회2() 끝

} // class 끝