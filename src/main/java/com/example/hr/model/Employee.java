package com.example.hr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    // 사번을 기본 키로 사용 (String 타입)
    @Id
    @Column(name = "employee_number", nullable = false, unique = true)
    private String employeeNumber;  // 예: "A12345"
    private String name;
    private String email;
    private String password;

    // 사용자 권한 추가 (ADMIN, APPROVER, USER)
    @Enumerated(EnumType.STRING)
    private Role role;

    // 기본 연차 정보 (필요시)
    private int totalAnnualLeave = 15;
    private int usedAnnualLeave = 0;

    // 직원은 하나의 조직에 속함 (다대일 관계)
    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;
}
