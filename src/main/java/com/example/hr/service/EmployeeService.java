package com.example.hr.service;

import com.example.hr.model.AnnualLeave;
import com.example.hr.model.Employee;
import com.example.hr.repository.AnnualLeaveRopository;
import com.example.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AnnualLeaveRopository annualLeaveRopository;

    // 직원 연차 조회
    public int getRemainingAnnualLeave(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("직원 정보를 찾을 수 없습니다" ));

        return employee.getTotalAnnualLeave() - employee.getUsedAnnualLeave();
    }

    // 직원 목록 연차 조회
    public List<AnnualLeave> getAnnualLeaveHistory(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("직원을 찾을 수 없습니다."));
        return annualLeaveRopository.findByEmployee(employee);
    }

    // 연차 등록
    public boolean requestAnnualLeave(String email, LocalDate leaveDate, String reason) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("직원을 찾을 수 없습니다."));

        // 남은 연차
        int remainingLeave = employee.getTotalAnnualLeave() - employee.getUsedAnnualLeave();

        // 연차 사용 가능 여부 체크
        if(remainingLeave <=0 || leaveDate.isBefore(LocalDate.now())){
            return false; // 잔여 연차 부족 또는 과거 날짜 선택 시 불가능
        }

        // 연차 기록 추가
        AnnualLeave annualLeave = new AnnualLeave();
        annualLeave.setEmployee(employee);
        annualLeave.setLeaveDate(leaveDate);
        annualLeave.setReason(reason);
        annualLeaveRopository.save(annualLeave);

        // 사용 연차 증가
        employee.setUsedAnnualLeave(employee.getUsedAnnualLeave() + 1);
        employeeRepository.save(employee);

        return true;
    }

    // 연차 수정
    @Transactional
    public boolean updateAnnualLeave(Long leaveId, LocalDate newLeaveDate, String newReason) {
        AnnualLeave annualLeave = annualLeaveRopository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("연차 기록을 찾을 수 없습니다."));

        // 이미 사용한(과거 날짜) 연차는 수정 불가
        if (annualLeave.getLeaveDate().isBefore(LocalDate.now())) {
            return false;
        }

        // 새로운 날짜가 과거 날짜인지 확인
        if (newLeaveDate.isBefore(LocalDate.now())) {
            return false;
        }

        // 연차 정보 수정
        annualLeave.setLeaveDate(newLeaveDate);
        annualLeave.setReason(newReason);
        annualLeaveRopository.save(annualLeave);

        return true;
    }

    public boolean deleteAnnualLeave(Long leaveId) {
        AnnualLeave annualLeave = annualLeaveRopository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("연차 기록을 찾을 수 없습니다."));

        // 오늘 날짜 이전의 연차는 삭제 불가
        if (annualLeave.getLeaveDate().isBefore(LocalDate.now())) {
            return false;
        }

        Employee employee = annualLeave.getEmployee();

        // 사용할 연차 개수 복구
        employee.setUsedAnnualLeave((employee.getUsedAnnualLeave() - 1));
        employeeRepository.save(employee);

        // 연차 기록 삭제
        annualLeaveRopository.delete(annualLeave);
        return true;
    }
}
