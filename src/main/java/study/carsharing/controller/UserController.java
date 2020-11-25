package study.carsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.carsharing.domain.User;
import study.carsharing.form.JoinForm;
import study.carsharing.form.LoginForm;
import study.carsharing.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public String join(JoinForm joinForm) {
        User user = new User();
        user.setEmail(joinForm.getEmail());
        user.setName(joinForm.getName());
        user.setPassword(joinForm.getPassword());
        userService.save(user);

        return "redirect:/login";
    }
}
