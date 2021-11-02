package org.junyharang.boardstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data @AllArgsConstructor @Builder
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;  // 첫 Page 번호
        this.size = 20; // 페이지당 출력될 개수
    } // 생성자 끝

    public Pageable getPageable(Sort sort){

        return PageRequest.of(page -1, size, sort);

    } // getPageable() 끝

} // class 끝
