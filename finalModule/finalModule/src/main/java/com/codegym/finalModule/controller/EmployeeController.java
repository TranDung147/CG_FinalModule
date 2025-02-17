package com.codegym.finalModule.controller;

import com.codegym.finalModule.service.Class.EmployeeService;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

<<<<<<< Updated upstream
//    @PostMapping("delete/{id}")
//    public String deleteEmployee(@PathVariable("employeeID") Integer employeeID, RedirectAttributes redirectAttributes) {
//        try {
//            employeeService.deleteEmployeeByID(employeeID);
//            redirectAttributes.addFlashAttribute("message", "Xóa thành công khuyến mãi!");
//        } catch (RuntimeException e) {
//            redirectAttributes.addFlashAttribute("error", "Xóa thất bại. Có lỗi xảy ra: " + e.getMessage());
//        }
//        return "redirect:/";
//    }
=======
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
>>>>>>> Stashed changes
}
