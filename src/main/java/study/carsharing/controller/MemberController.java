package study.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.carsharing.configuration.JwtTokenProvider;
import study.carsharing.domain.Member;
import study.carsharing.form.CustomResponse;
import study.carsharing.form.CustomToken;
import study.carsharing.service.MemberService;

import java.util.Map;
import java.util.Optional;

// https://webfirewood.tistory.com/115 참고
@RestController
public class MemberController {
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
    public ResponseEntity<CustomResponse> login(@RequestBody Map<String, String> member) {
        CustomResponse customResponse = new CustomResponse();
        HttpHeaders httpHeaders = new HttpHeaders();

        Optional<Member> findMember = memberService.findByEmail(member.get("email"));
        if(findMember.isEmpty()) {
            customResponse.setMessage("Not valid eamil or password");
            return new ResponseEntity<CustomResponse>(customResponse, httpHeaders, HttpStatus.FORBIDDEN);
        }

        if (!passwordEncoder.matches(member.get("password"), findMember.get().getPassword())) {
            customResponse.setMessage("Not valid eamil or password");
            return new ResponseEntity<CustomResponse>(customResponse, httpHeaders, HttpStatus.FORBIDDEN);
        }
        customResponse.setMessage("Login Success");
        customResponse.setData(new CustomToken(jwtTokenProvider.createToken(findMember.get().getEmail(), findMember.get().getPassword())));

        return new ResponseEntity<CustomResponse>(customResponse, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("MEMBER");
        memberService.save(member);

        return "login";
    }

    @GetMapping("/member")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "member/member";
    }
}
