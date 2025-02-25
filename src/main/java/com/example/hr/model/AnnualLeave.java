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

    @Column(name = "leave_date", nullable = false)
    private LocalDate leaveDate; // 연차 사용 날짜

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public boolean updateLeave(LocalDate newLeaveDate, String newReason) {
        if(newLeaveDate.isBefore(LocalDate.now())) {
            return false; // 이미 지난 날짜는 수정 불가
        }
        this.leaveDate = newLeaveDate;
        this.reason = newReason;
        return true;
    }

}
