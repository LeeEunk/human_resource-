package com.example.hr.repository;

import com.example.hr.model.AnnualLeave;
import com.example.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualLeaveRopository extends JpaRepository<AnnualLeave, Long> {
    List<AnnualLeave> findByEmployee(Employee employee);
}
