package com.example.hr.service;

import com.example.hr.model.Employee;
import com.example.hr.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입 처리
    public Employee register(Employee employee) {
        return userRepository.save(employee);
    }

    // 사번(employeeNumber) 기반 로그인 처리
    public Employee login(String employeeNumber, String password) throws Exception {
        Employee employee = userRepository.findById(employeeNumber).orElseThrow(() -> new Exception("존재하지않는 사번입니다."));
        if (employee.getPassword().equals(password)) {
            return employee;
        }else{
            throw new Exception("비밀번호가 맞지 않습니다.");
        }
    }
}

