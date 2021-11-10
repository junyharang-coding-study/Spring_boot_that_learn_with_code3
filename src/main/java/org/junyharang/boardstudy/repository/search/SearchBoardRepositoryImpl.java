package org.junyharang.boardstudy.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.QBoard;
import org.junyharang.boardstudy.entity.QMember;
import org.junyharang.boardstudy.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2 public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        // QuerydslRepositorySupport에 생성자가 존재하므로, Class내에 super()를 이용하여 호출
        super(Board.class);
    } // 생성자 끝

    @Override
    public Board search1() {
        log.info("search1 Method가 동작 중 입니다.");

        QBoard qBoard = QBoard.board;
        QReply qReply = QReply.reply;
        QMember qMember = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(qBoard);
        jpqlQuery.leftJoin(qMember).on(qBoard.writer.eq(qMember));
        jpqlQuery.leftJoin(qReply).on(qReply.board.eq(qBoard));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(qBoard, qMember.email, qReply.count());
        tuple.groupBy(qBoard);

        log.info("-------------------------------------------");
        log.info(jpqlQuery);
        log.info("-------------------------------------------");

        log.info("-------------------------------------------");
        log.info(tuple);
        log.info("-------------------------------------------");

//        List<Board> result = jpqlQuery.fetch();

        List<Tuple> result = tuple.fetch();

        log.info("-------------------------------------------");
        log.info(result);
        log.info("-------------------------------------------");

        return null;
    } // search1() 끝

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage()가 동작 중 입니다!");

        return null;
    }
} // class 끝
