package study.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.carsharing.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByEmail(String email);
}
