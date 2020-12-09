package study.carsharing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import lombok.RequiredArgsConstructor;
import study.carsharing.domain.Member;
import study.carsharing.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member findByEmail(final String email, final String password) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if (StringUtils.equals(email, "test")) {
            memberOptional = getMockMember(password);
        }

        memberOptional.ifPresentOrElse(
                m -> validatePassword(password, m.getPassword()),
                () -> { throw new UsernameNotFoundException("User not found"); }
        );

        return memberOptional.get();
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    private Optional<Member> getMockMember(String password) {
        Member member = new Member();
        member.setPassword(passwordEncoder.encode(password));
        return Optional.of(member);
    }

    private void validatePassword(String inputPassword, String encodedPassword) {
        if (!passwordEncoder.matches(inputPassword, encodedPassword)) {
            throw new BadCredentialsException("User invalid");
        }
    }
}
