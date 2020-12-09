package study.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.carsharing.configuration.JwtTokenProvider;
import study.carsharing.domain.Member;
import study.carsharing.form.CustomToken;
import study.carsharing.service.MemberService;

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

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(@RequestBody Member memberDto) {
        Member member = memberService.findByEmail(memberDto.getEmail(), memberDto.getPassword());
        return ResponseEntity.ok().body(new CustomToken(jwtTokenProvider.createToken(member.getEmail(),
                                                                                     member.getId(),
                                                                                     member.getRole())));
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
