package study.carsharing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import study.carsharing.configuration.JwtTokenProvider;
import study.carsharing.domain.Member;
import study.carsharing.form.CustomToken;
import study.carsharing.service.MemberService;

import java.util.Map;
import java.util.Optional;

@Api(tags = {"Member"})
@RestController
public class MemberController {
    // https://webfirewood.tistory.com/115 참고
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> member) {
        Optional<Member> findMember = memberService.findByEmail(member.get("email"));
        if(findMember.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!member.get("password").equals(findMember.get().getPassword())) {
            throw new BadCredentialsException("User invalid");
        }

        CustomToken token = new CustomToken(jwtTokenProvider.createToken(findMember.get().getEmail(), findMember.get().getId(), findMember.get().getRole()));
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody Member member) {
        memberService.save(member);
        return ResponseEntity.ok().body(member);
    }

    @ApiOperation(value = "전체 회원 조회", httpMethod = "GET", notes = "모든 회원을 조회함")
    @GetMapping("/member")
    public ResponseEntity members() {
        return ResponseEntity.ok().body(memberService.findAll());
    }

    @ApiOperation(value = "회원 조회", httpMethod = "GET", notes = "특정 ID를 가진 회원을 조회함")
    @GetMapping("/member/{id}")
    public ResponseEntity members(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(memberService.findById(id));
    }
}
