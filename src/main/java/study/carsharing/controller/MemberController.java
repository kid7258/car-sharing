package study.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.carsharing.domain.Member;
import study.carsharing.form.LoginForm;
import study.carsharing.service.MemberService;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm) {
        // spring security 사용하고 싶음

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String join(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("MEMBER");
        memberService.save(member);
        System.out.println("test git PR");

        return "redirect:/login";
    }

    @GetMapping("/member")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "member";
    }
}
