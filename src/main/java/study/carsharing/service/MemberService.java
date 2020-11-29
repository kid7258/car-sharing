package study.carsharing.service;

import study.carsharing.domain.Member;

import java.util.List;

public interface MemberService {
    Member findByEmail(String email);
    Member save(Member member);
    List<Member> findAll();
}
