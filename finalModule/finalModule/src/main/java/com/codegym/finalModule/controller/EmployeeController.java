package com.codegym.finalModule.controller;

import com.codegym.finalModule.dto.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/create")
    public ModelAndView showAddEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("/admin/employee/addemployee");
        modelAndView.addObject("employeeDTO", new EmployeeDTO());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       RedirectAttributes redirectAttributes ,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/admin/employee/addemployee");
        }
        this.employeeService.save(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Employee created successfully");
        // Em muốn thêm dòng này ở đâu để thông báo thành công
        return new ModelAndView("redirect:/employees");  // Trả về đường dẫn uri dẫn đến list

    }



}
