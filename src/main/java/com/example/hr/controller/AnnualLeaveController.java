package com.example.hr.controller;

import com.example.hr.model.AnnualLeave;
import com.example.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/annual-leave")
@RequiredArgsConstructor
public class AnnualLeaveController {
    private final EmployeeService employeeService;

    @GetMapping("/{email}")
    public String viewAnnualLeave(@PathVariable("email") String email, Model model) {
        int remainingLeave = employeeService.getRemainingAnnualLeave(email);
        List<AnnualLeave> leaveHistory = employeeService.getAnnualLeaveHistory(email);

        model.addAttribute("remainingLeave", remainingLeave);
        model.addAttribute("leaveHistory", leaveHistory);
        model.addAttribute("email", email);

        return "annual_leave"; // Thymeleaf 템플릿 반환
    }


    // 연차 등록 API
    @PostMapping("/request")
    public String requestAnnualLeave(@RequestParam("email") String email,
                                     @RequestParam("leaveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate leaveDate,
                                     @RequestParam("reason") String reason,
                                     RedirectAttributes redirectAttributes){
        boolean success = employeeService.requestAnnualLeave(email, leaveDate, reason);

        if(success) {
            redirectAttributes.addFlashAttribute("message", "연차가 성공적으로 등록되었습니다.");
        }else {
            redirectAttributes.addFlashAttribute("error", "연차 신청이 불가능합니다. (잔여 연차 부족 또는 과거 날짜 선택)");
        }

        return "redirect:/annual-leave/" + email;
    }

    // 연차 수정 API
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAnnualLeave(
            @PathVariable("id") Long id,
            @RequestBody AnnualLeave request) {

        // ID가 일치하는지 확인
        if (!id.equals(request.getId())) {
            return ResponseEntity.badRequest().body("ID 불일치로 인해 연차를 수정할 수 없습니다.");
        }

        boolean success = employeeService.updateAnnualLeave(id, request.getLeaveDate(), request.getReason());

        if (success) {
            return ResponseEntity.ok("연차가 성공적으로 수정되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("연차 수정이 불가능합니다. (이미 지난 연차는 변경할 수 없습니다.)");
        }
    }



    // 연차 삭제 API (id 기준으로 삭제)
    @PostMapping("/delete/{id}")
    public String deleteAnnualLeave(@PathVariable("id") Long id,
                                    @RequestParam("email") String email,
                                    RedirectAttributes redirectAttributes) {
        boolean success = employeeService.deleteAnnualLeave(id);

        if(success) {
            redirectAttributes.addFlashAttribute("message", "연차가 성공적으로 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "이미 지난 연차는 삭제할 수 없습니다.");
        }
        return "redirect:/annual-leave/" + email;
    }
}
