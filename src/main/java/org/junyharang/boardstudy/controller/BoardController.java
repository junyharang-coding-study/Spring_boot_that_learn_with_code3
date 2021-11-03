package org.junyharang.boardstudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junyharang.boardstudy.dto.PageRequestDTO;
import org.junyharang.boardstudy.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2 @RequiredArgsConstructor
@RequestMapping("/board") @Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list") public void list(PageRequestDTO pageRequestDTO, Model model) {  // 목록 Controller
        log.info("목록 정보 : " + pageRequestDTO);

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

} // class 끝
