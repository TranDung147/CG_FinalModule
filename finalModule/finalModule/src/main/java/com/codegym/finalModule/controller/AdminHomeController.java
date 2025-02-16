package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Admin")
public class AdminHomeController {

    @Autowired
    private IEmployeeService iemployeeService;

    @GetMapping()
    public String showAdminHome() {
        return "admin/layout/layout";
    }
@GetMapping("/employee-manager")
public String EmployeeList(
        Model model,
        @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
        @RequestParam(name = "type", required = false, defaultValue = "all") String type,
        @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo){
    Page<Employee> list = iemployeeService.searchUsers(keyword, type, pageNo);
    model.addAttribute("list", list.getContent());
    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", list.getTotalPages());
    model.addAttribute("keyword", keyword);
    model.addAttribute("type", type);

    return "admin/employee/listemployee";
}
    @PostMapping("/disable")
    public ResponseEntity<?> disableEmployees(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> employeeIds = request.get("employeeIds");

        if (employeeIds == null || employeeIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Không có nhân viên nào được chọn"));
        }

        List<Employee> employees = iemployeeService.findByIds(employeeIds);
        for (Employee emp : employees) {
            if (!emp.isDisabled()) {
                emp.setDisabled(true);
            } else {
                emp.setDisabled(false);
            }
             // Chuyển trạng thái sang vô hiệu hóa
        }
        iemployeeService .saveAll(employees);

        return ResponseEntity.ok(Map.of("success", true));
    }

}

