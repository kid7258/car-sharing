package study.carsharing.service;

import org.springframework.stereotype.Service;
import study.carsharing.domain.User;
import study.carsharing.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(User user) {
        return userRepository.findByEmail(user.getEmail()).get();
    }
}
