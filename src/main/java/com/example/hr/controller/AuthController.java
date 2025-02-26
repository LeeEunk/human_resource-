package com.example.hr.controller;

import com.example.hr.model.Employee;
import com.example.hr.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "authentication-register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Employee employee) {
        userRepository.save(employee);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "authentication-login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password) {
        Employee employee = userRepository.findByEmail(email);
        if (employee != null && employee.getPassword().equals(password)) {
            return "index";  // 로그인 성공 시 index.html로 리다이렉트
        }
        return "redirect:/login?error";  // 로그인 실패 시 다시 로그인 페이지로
    }

}
