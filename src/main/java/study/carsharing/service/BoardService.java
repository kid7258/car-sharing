package study.carsharing.service;

import study.carsharing.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board save(Board board);
    List<Board> findAll();
    Optional<Board> findById(Long id);
}
