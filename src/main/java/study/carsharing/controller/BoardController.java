package study.carsharing.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.carsharing.domain.Board;
import study.carsharing.service.BoardService;
import study.carsharing.service.MemberService;

import java.time.LocalDateTime;

@Controller
public class BoardController {
    private BoardService boardService;
    private MemberService memberService;

    public BoardController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "board/board";
    }

    @GetMapping("/board/{id}")
    public String boardById(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", memberService.findByEmail(auth.getName()).get().getName());
        model.addAttribute("board", boardService.findById(id));

        return "board/boardDetail";
    }

    @GetMapping("/board/new")
    public String boardForm() {
        return "board/boardForm";
    }

    @PostMapping("/board/new")
    public String create(Board board) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        board.setMemberId(memberService.findByEmail(auth.getName()).get().getId());
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(LocalDateTime.now());

        boardService.save(board);
        return "board/board";
    }
}
