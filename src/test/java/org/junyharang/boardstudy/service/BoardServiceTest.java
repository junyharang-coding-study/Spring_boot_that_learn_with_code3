package org.junyharang.boardstudy.service;

import org.junit.jupiter.api.Test;
import org.junyharang.boardstudy.dto.BoardDTO;
import org.junyharang.boardstudy.dto.PageRequestDTO;
import org.junyharang.boardstudy.dto.PageResponseDTO;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest class BoardServiceTest {

    @Autowired private BoardService boardService;

    @Test public void 게시물_등록() {

        BoardDTO dto = BoardDTO.builder()
                .title("게시물 등록 테스트 입니다!")
                .content("테스트 중이에요!")
                // 현재 DB에 존재하는 회원 Email
                .writerEmail("junyharang1@junyharang.com")
                .build();

        Long bno = boardService.register(dto);

    } // 게시물_등록() 끝

    @Test public void 목록_출력() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponseDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        } // for 문 끝

    } // 목록_출력() 끝

    @Test public void 게시물_조회() {

        long bno = 100L;

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);

    } // 게시물_조회() 끝

    @Test public void 게시글_삭제() {

        long bno = 104L;

        boardService.removeWithReplies(bno);

    } // 게시글_삭제() 끝

} // class 끝