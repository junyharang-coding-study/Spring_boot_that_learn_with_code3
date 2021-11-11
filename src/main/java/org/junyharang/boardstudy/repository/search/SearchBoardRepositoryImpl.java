package org.junyharang.boardstudy.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.junyharang.boardstudy.entity.Board;
import org.junyharang.boardstudy.entity.QBoard;
import org.junyharang.boardstudy.entity.QMember;
import org.junyharang.boardstudy.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

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

        QBoard qBoard = QBoard.board;
        QReply qReply = QReply.reply;
        QMember qMember = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(qBoard);
        jpqlQuery.leftJoin(qMember).on(qBoard.writer.eq(qMember));
        jpqlQuery.leftJoin(qReply).on(qBoard.eq(qBoard));

        //Select b, w, count(r) From Board b LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b
        JPQLQuery<Tuple> tuple = jpqlQuery.select(qBoard, qMember, qReply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qBoard.bno.gt(0L);

        booleanBuilder.and(expression);

        if(type != null) { // 게시글 Type이 비어있지 않다면?
            // Type을 공백 기준으로 나눠서 배열에 각각 저장
            String[] typeArr = type.split("");

            // 검색 조건 작성
           BooleanBuilder conditionBuilder = new BooleanBuilder();

           for (String t : typeArr) {
               switch (t) {
                   case "t":
                       conditionBuilder.or(qBoard.title.contains(keyword));
                       break;
                   case "w":
                       conditionBuilder.or(qMember.email.contains(keyword));
                       break;
                   case "c":
                       conditionBuilder.or(qBoard.content.contains(keyword));
                       break;
               } // switch 끝
           } // for문 끝

           booleanBuilder.and(conditionBuilder);
        } // if문 끝

        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        //tuple.orderBy(board.bno.desc());
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(qBoard);

        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("count : " + count);

        return new PageImpl<Object[]>(result.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList()), pageable, count);
    } // searchPage() 끝
} // class 끝
