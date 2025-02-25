package com.example.hr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "annual_leave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnualLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate leaveDate; // 연차 사용 날짜
    private String reason;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
