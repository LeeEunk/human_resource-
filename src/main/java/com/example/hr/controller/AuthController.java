package com.example.hr.controller;

import com.example.hr.model.Employee;
import com.example.hr.repository.UserRepository;
import com.example.hr.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "authentication-register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Employee employee) {
        authService.register(employee);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "authentication-login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("employeeNumber") String employeeNumber,
                            @RequestParam("password") String password,
                            RedirectAttributes redirectAttributes) throws Exception {
        try {
            Employee employee = authService.login(employeeNumber, password);
            if (employee != null) {
                // 로그인 성공 시 index.html로 리다이렉트
                return "redirect:/index.html";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
                return "redirect:/login";
            }
        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login";
        }
    }

}
