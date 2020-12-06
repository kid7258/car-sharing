package study.carsharing.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.carsharing.repository.BoardRepository;
import study.carsharing.repository.MemberRepository;
import study.carsharing.service.BoardService;
import study.carsharing.service.BoardServiceImpl;
import study.carsharing.service.MemberService;
import study.carsharing.service.MemberServiceImpl;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, BoardRepository boardRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository);
    }
}
