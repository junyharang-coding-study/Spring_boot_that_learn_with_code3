package org.junyharang.boardstudy.repository;

import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // @Modifying = @Query로 작성된 변경, 삭제 Query Method를 사용할 때 필요
    // 주로 벌크 연산 시 사용 (기본적으로 제공되는 Query Method나, Method Naming으로 파생되는 Query Method에는 적용 되지 않는다.)
    // Board 삭제 시 댓글들 삭제
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno ")
    void deleteByBno(@Param("bno") Long bno);

    // 게시물로 댓글 목록 가져오기
    List<Board> getRepliesByBoardOrderByRno(Board board);
} // interface 끝


