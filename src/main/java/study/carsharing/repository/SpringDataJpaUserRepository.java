package study.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.carsharing.domain.User;

import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<User, Long>, UserRepository {
    @Override
    Optional<User> findByEmail(String email);
}
