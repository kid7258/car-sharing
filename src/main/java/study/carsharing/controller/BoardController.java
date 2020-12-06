package study.carsharing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.carsharing.domain.Board;
import study.carsharing.service.BoardService;
import study.carsharing.service.MemberService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class BoardController {
    private BoardService boardService;
    private MemberService memberService;

    public BoardController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @GetMapping("/board")
    public ResponseEntity board() {
        return ResponseEntity.ok().body(boardService.findAll());
    }

    @GetMapping("/board/{id}")
    public ResponseEntity boardById(@PathVariable Long id) {
        return ResponseEntity.ok().body(boardService.findById(id));
    }

    @PostMapping("/board/new")
    public ResponseEntity create(@RequestBody Board board) {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(LocalDateTime.now());

        boardService.save(board);
        return ResponseEntity.ok().body(board);
    }
}
