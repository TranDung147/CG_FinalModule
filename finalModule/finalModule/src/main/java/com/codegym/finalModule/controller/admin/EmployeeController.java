package com.codegym.finalModule.controller.admin;


import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import jakarta.validation.Valid;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin/employee-manager")
public class EmployeeController {
  
    private final IEmployeeService employeeService;
    public EmployeeController(IEmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/create")
    public ModelAndView showAddEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("admin/employee/addEmployee");
        modelAndView.addObject("employeeDTO", new EmployeeDTO());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult ,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/employee/addEmployee");
        }
        this.employeeService.save(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Thêm nhân viên thành công ");
        return new ModelAndView("redirect:/Admin/employee-manager");

    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditEmployeeForm(@PathVariable int id) {
       ModelAndView modelAndView = new ModelAndView("admin/employee/editEmployee");
        EmployeeDTO employeeDTO = this.employeeService.findDTOById(id);
        modelAndView.addObject("employeeDTO", employeeDTO);
       return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/employee/editEmployee");
        }
        this.employeeService.update(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Cập nhật nhân viên thành công ");
        return new ModelAndView("redirect:/Admin/employee-manager");
    }
    @PostMapping("/disable")
    public ResponseEntity<?> disableEmployees(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> employeeIds = request.get("employeeIds");

        if (employeeIds == null || employeeIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Không có nhân viên nào được chọn"));
        }

        List<Employee> employees = employeeService.findByIds(employeeIds);
        for (Employee emp : employees) {
            if (!emp.getIsDisabled()) {
                emp.setIsDisabled(true);
            } else {
                emp.setIsDisabled(false);
            }
            // Chuyển trạng thái sang vô hiệu hóa
        }
        employeeService .saveAll(employees);

        return ResponseEntity.ok(Map.of("success", true));
    }
}
