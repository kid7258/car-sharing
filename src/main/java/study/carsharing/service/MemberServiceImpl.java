package study.carsharing.service;

import org.springframework.stereotype.Service;
import study.carsharing.domain.Member;
import study.carsharing.repository.MemberRepository;

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
    public Member findMember(Member member) {
        return memberRepository.findByEmail(member.getEmail()).get();
    }
}
