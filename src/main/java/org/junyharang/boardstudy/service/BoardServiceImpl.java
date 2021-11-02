package org.junyharang.boardstudy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junyharang.boardstudy.dto.BoardDTO;
import org.junyharang.boardstudy.dto.PageRequestDTO;
import org.junyharang.boardstudy.dto.PageResponseDTO;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Member;
import org.junyharang.boardstudy.repository.BoardRepository;
import org.junyharang.boardstudy.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service @RequiredArgsConstructor @Log4j2
public class BoardServiceImpl implements BoardService {

    // 자동 주입 final
    private final BoardRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);

        Board board = dtoToEntity(dto);
        
        repository.save(board);
        
        return board.getBno();
    } // register() 끝

    @Override
    public PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResponseDTO<>(result, fn);
    } // getList() 끝

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    } // get() 끝

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {   // 삭제 기능 구현 및 트랜잭션 추가

        // 댓글 부터 삭제
        replyRepository.deleteByBno(bno);

        // 게시글 삭제
        repository.deleteById(bno);
    } // removeWithReplies() 끝
} // cass 끝
