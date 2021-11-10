package org.junyharang.boardstudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junyharang.boardstudy.dto.BoardDTO;
import org.junyharang.boardstudy.dto.PageRequestDTO;
import org.junyharang.boardstudy.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2 @RequiredArgsConstructor
@RequestMapping("/board") @Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list") public void list(PageRequestDTO pageRequestDTO, Model model) {  // 목록 Controller
        log.info("목록 정보 : " + pageRequestDTO);

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model) {
        log.info("게시글 번호 :" + bno);

        BoardDTO boardDTO = BoardService.get(bno);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);
    } // read() 끝

    @PostMapping("/remove") public String remove(long bno, RedirectAttributes redirectAttributes) {
        log.info("게시글 번호 : " + bno);

        boardService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    } // remove() 끝

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        log.info("POST의 수정 Method 실행 중 입니다.");
        log.info("dto : " + dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    } // modify() 끝
} // class 끝
