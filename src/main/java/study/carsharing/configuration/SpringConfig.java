package study.carsharing.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.carsharing.repository.BoardRepository;
import study.carsharing.repository.CarRepository;
import study.carsharing.repository.MemberRepository;
import study.carsharing.service.*;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CarRepository carRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, BoardRepository boardRepository, CarRepository carRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.carRepository = carRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository);
    }

    @Bean
    public CarService carService() {
        return new CarServiceImpl(carRepository);
    }
}
