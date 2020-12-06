package study.carsharing.service;

import study.carsharing.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
    Member save(Member member);
    List<Member> findAll();
}
