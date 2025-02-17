package com.codegym.finalModule.controller;

import com.codegym.finalModule.dto.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import jakarta.validation.Valid;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Admin/employee-manager")
public class EmployeeController {
  
    private final IEmployeeService employeeService;
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/create")
    public ModelAndView showAddEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("admin/employee/addemployee");
        modelAndView.addObject("employeeDTO", new EmployeeDTO());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult ,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/employee/addemployee");
        }
        this.employeeService.save(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Thêm nhân viên thành công ");
        return new ModelAndView("redirect:/Admin/employee-manager");

    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditEmployeeForm(@PathVariable int id) {
       ModelAndView modelAndView = new ModelAndView("admin/employee/editemployee");
        EmployeeDTO employeeDTO = this.employeeService.findDTOById(id);
        modelAndView.addObject("employeeDTO", employeeDTO);
       return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/employee/editemployee");
        }
        this.employeeService.update(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Cập nhật nhân viên thành công ");
        return new ModelAndView("redirect:/Admin/employee-manager");
    }

}
