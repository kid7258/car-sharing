package study.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.carsharing.domain.Board;

public interface SpringDataJpaBoardRepository extends JpaRepository<Board, Long>, BoardRepository {

}
