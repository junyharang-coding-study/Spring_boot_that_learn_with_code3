package org.junyharang.boardstudy.repository.search;

import org.junyharang.boardstudy.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    //Board Type 객체 반환 Method 선언
    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}// interface 끝
