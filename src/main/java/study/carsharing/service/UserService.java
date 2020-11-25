package study.carsharing.service;

import study.carsharing.domain.User;

public interface UserService {
    User findUser(User user);
    User save(User user);
}
