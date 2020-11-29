package study.carsharing.service;

import org.springframework.stereotype.Service;
import study.carsharing.domain.Member;
import study.carsharing.repository.MemberRepository;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
