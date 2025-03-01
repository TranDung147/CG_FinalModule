package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.model.EmployeePosition;
import com.codegym.finalModule.service.impl.EmployeePositionService;
import jakarta.validation.Valid;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final EmployeePositionService employeePositionService;
    public EmployeeController(IEmployeeService employeeService ,
                              EmployeePositionService employeePositionService) {
        this.employeeService = employeeService;
        this.employeePositionService = employeePositionService;
    }

    @ModelAttribute("employeePositions")
    public List<EmployeePosition> getEmployeePositions() {
        return this.employeePositionService.getEmployeePositions();
    }

    @GetMapping("/employee-manager")
    public ModelAndView employeeList(@RequestParam(name = "searchField", required = false) String field,
                                     @RequestParam(name = "searchInput",
                                             required = false,
                                             defaultValue = "") String keyword,
                                     @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                     @RequestParam(name = "size", required = false, defaultValue = "3") int size) {
        ModelAndView modelAndView = new ModelAndView("admin/employee/listEmployee");

        Page<Employee> employeesPage = this.employeeService.findAll(page, size); // Gán giá trị mặc định

        String filterKeyWord = keyword.trim();
        if (!filterKeyWord.isEmpty()) {
            employeesPage = this.employeeService.searchByFieldAndKeyword(field, filterKeyWord, page, size);
        }

        if (employeesPage.isEmpty()) {
            modelAndView.addObject("message", "Không có nhân viên phù hợp với dữ liệu.");
        }

        modelAndView.addObject("employees", employeesPage);
        modelAndView.addObject("field", field);
        modelAndView.addObject("filterKeyWord", filterKeyWord);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", employeesPage.getTotalPages());

        // Thêm employeeDTO để sử dụng trong modal
        modelAndView.addObject("employeeDTO", new EmployeeDTO());

        return modelAndView;
    }

    // Giữ lại phương thức hiện tại để tương thích ngược (có thể loại bỏ sau)
    @GetMapping("/employee-manager/create")
    public ModelAndView showAddEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("admin/employee/addEmployee");
        modelAndView.addObject("employeeDTO", new EmployeeDTO());
        return modelAndView;
    }

    @PostMapping("/employee-manager/create")
    public ModelAndView createEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        // Xử lý lỗi validation khi submit từ modal
        if (bindingResult.hasErrors()) {
            // Nếu lỗi, quay lại trang danh sách với lỗi
            ModelAndView modelAndView = new ModelAndView("admin/employee/listEmployee");

            // Cần phải nạp lại các dữ liệu cần thiết cho trang
            Page<Employee> employeesPage = this.employeeService.findAll(1, 3);
            modelAndView.addObject("employees", employeesPage);
            modelAndView.addObject("field", "");
            modelAndView.addObject("filterKeyWord", "");
            modelAndView.addObject("currentPage", 1);
            modelAndView.addObject("totalPages", employeesPage.getTotalPages());

            // Thêm thông báo lỗi
            modelAndView.addObject("validationErrors", true);

            return modelAndView;
        }

        this.employeeService.save(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Thêm nhân viên thành công");
        return new ModelAndView("redirect:/Admin/employee-manager");
    }

    @GetMapping("/employee-manager/edit/{id}")
    public ModelAndView showEditEmployeeForm(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Boolean isExist = this.employeeService.findById(id);

        if(!isExist){
            redirectAttributes.addFlashAttribute("message", "Nhân viên không tồn tại");
            return new ModelAndView("redirect:/Admin/employee-manager");
        }
        ModelAndView modelAndView = new ModelAndView("admin/employee/editEmployee");
        EmployeeDTO employeeDTO = this.employeeService.findDTOById(id);
        modelAndView.addObject("employeeDTO", employeeDTO);
        return modelAndView;
    }

    @PostMapping("/employee-manager/edit")
    public ModelAndView updateEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/employee/editEmployee");
        }

        this.employeeService.update(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Cập nhật nhân viên thành công");
        return new ModelAndView("redirect:/Admin/employee-manager");
    }

    @PostMapping("/employee-manager/disable")
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
        employeeService.saveAll(employees);

        return ResponseEntity.ok(Map.of("success", true));
    }
}