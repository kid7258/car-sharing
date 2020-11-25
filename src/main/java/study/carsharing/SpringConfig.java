package study.carsharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import study.carsharing.repository.UserRepository;
import study.carsharing.service.UserService;
import study.carsharing.service.UserServiceImpl;

public class SpringConfig {
    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository);
    }
}
