package study.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import study.carsharing.configuration.JwtTokenProvider;
import study.carsharing.domain.Member;
import study.carsharing.form.CustomToken;
import study.carsharing.service.MemberService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {
    // https://webfirewood.tistory.com/115 참고
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> member) {
        System.out.println(member);

        Optional<Member> findMember = memberService.findByEmail(member.get("email"));
        if(findMember.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(member.get("password"), findMember.get().getPassword())) {
            throw new BadCredentialsException("User invalid");
        }

        return ResponseEntity.ok().body(new CustomToken(jwtTokenProvider.createToken(findMember.get().getEmail(), findMember.get().getId(), findMember.get().getRole())));
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody Member member) {
        System.out.println(member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("MEMBER");
        memberService.save(member);

        return ResponseEntity.ok().body(member);
    }

    @GetMapping("/member")
    public ResponseEntity members() {
        return ResponseEntity.ok().body(memberService.findAll());
    }

    @GetMapping("/member/{id}")
    public ResponseEntity members(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(memberService.findById(id));
    }
}
