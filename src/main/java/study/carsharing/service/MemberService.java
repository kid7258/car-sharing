package study.carsharing.service;

import study.carsharing.domain.Member;

public interface MemberService {
    Member findMember(Member member);
    Member save(Member member);
}
