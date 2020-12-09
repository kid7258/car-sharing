package study.carsharing.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.carsharing.repository.BoardRepository;
import study.carsharing.repository.MemberRepository;
import study.carsharing.service.BoardService;
import study.carsharing.service.BoardServiceImpl;

@Configuration
public class SpringConfig {
    private final BoardRepository boardRepository;

    @Autowired
    public SpringConfig(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository);
    }
}
