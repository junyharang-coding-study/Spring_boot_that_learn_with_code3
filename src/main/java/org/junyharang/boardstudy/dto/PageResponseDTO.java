package org.junyharang.boardstudy.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<DTO, EN> {

    // DTO List
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalpage;

    // 현재 페이지 번호
    private int page;

    // 목록 크기(Size)
    private int size;

    // 시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    // 이전과 다음
    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalpage = result.getTotalPages();
        
        makePageList(result.getPageable());
    } // PageResponseDTO() 끝

    private void makePageList(Pageable pageable) {

        // 0부터 시작하기 때문에 1을 더한다.
        this.page = pageable.getPageNumber() + 1;

        this.size = pageable.getPageSize();

        // 임시 끝 Page 정보
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalpage > tempEnd ? tempEnd : totalpage;

        next = totalpage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }// makePageList() 끝

} // class 끝
