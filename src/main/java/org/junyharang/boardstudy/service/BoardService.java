package org.junyharang.boardstudy.service;

import org.junyharang.boardstudy.dto.BoardDTO;
import org.junyharang.boardstudy.dto.PageRequestDTO;
import org.junyharang.boardstudy.dto.PageResponseDTO;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    // 목록 처리
    PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 조회 처리
    BoardDTO get(Long bno);

    // 삭제 처리
    void removeWithReplies(Long bno);

    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        // dto값을 Entity로 변환 해 주는 작업
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;

    } // dtoToEntity() 끝

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerName(board.getWriter().getName())
                .writerEmail(board.getWriter().getEmail())
                // 결과값이 long으로 나오기 때문에 int로 처리하도록 처리
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;

    } // entitytoDTO() 끝

    void modify(BoardDTO boardDTO);

} // interface 끝
