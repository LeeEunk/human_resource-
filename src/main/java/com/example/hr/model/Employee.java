package com.example.hr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private int totalAnnualLeave = 15; // 기본 연차 15일
    private int usedAnnualLeave = 0;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AnnualLeave> annualLeaves;
}
