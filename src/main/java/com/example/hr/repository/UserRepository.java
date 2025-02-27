package com.example.hr.repository;

import com.example.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Employee, String> {
    Employee findByEmail(String email);
}
