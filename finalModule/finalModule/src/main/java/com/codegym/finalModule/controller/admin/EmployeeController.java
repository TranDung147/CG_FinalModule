package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.model.EmployeePosition;
import com.codegym.finalModule.service.impl.EmployeePositionService;
import jakarta.validation.Valid;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final EmployeePositionService employeePositionService;

    public EmployeeController(IEmployeeService employeeService,
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

        Page<Employee> employeesPage = this.employeeService.findAll(page, size);

        String filterKeyWord = keyword.trim();
        if (!filterKeyWord.isEmpty() && field != null) {
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
        modelAndView.addObject("employeeDTO", new EmployeeDTO());
        modelAndView.addObject("employeePosition", new EmployeePosition());

        return modelAndView;
    }
    @GetMapping("/employee-manager/create")
    public ModelAndView showAddEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("admin/employee/listEmployee");
        modelAndView.addObject("employeeDTO", new EmployeeDTO());
        return modelAndView;
    }

    @PostMapping("/employee-manager/create")
    public ModelAndView createEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes ){
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/employee/listEmployee");
            modelAndView.addObject("employees",this.employeeService.findAll(1,3));
            modelAndView.addObject("employeeDTO",new EmployeeDTO());
            modelAndView.addObject("employeePosition", new EmployeePosition());
            return modelAndView;
        }
        this.employeeService.save(employeeDTO);
        redirectAttributes.addFlashAttribute("message", "Thêm nhân viên thành công.");
        return new ModelAndView("redirect:/Admin/employee-manager");
    }
    @GetMapping("/employee-manager/get/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeDTO> getEmployeeData(@PathVariable int id) {
        Boolean isExist = this.employeeService.findById(id);

        if (!isExist) {
            return ResponseEntity.notFound().build();
        }

        EmployeeDTO employeeDTO = this.employeeService.findDTOById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping("/employee-manager/edit")
    public ModelAndView updateEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes,
                                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(name = "size", required = false, defaultValue = "3") int size,
                                       @RequestParam(name = "searchField", required = false) String field,
                                       @RequestParam(name = "searchInput", required = false, defaultValue = "") String keyword) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/employee/listEmployee");
            String filterKeyWord = keyword.trim();
            Page<Employee> employeesPage;

            if (!filterKeyWord.isEmpty() && field != null) {
                employeesPage = this.employeeService.searchByFieldAndKeyword(field, filterKeyWord, page, size);
            } else {
                employeesPage = this.employeeService.findAll(page, size);
            }

            modelAndView.addObject("employees", employeesPage);
            modelAndView.addObject("field", field);
            modelAndView.addObject("filterKeyWord", filterKeyWord);
            modelAndView.addObject("currentPage", page);
            modelAndView.addObject("totalPages", employeesPage.getTotalPages());
            modelAndView.addObject("employeeDTO", employeeDTO);
            modelAndView.addObject("employeePosition", new EmployeePosition());
            modelAndView.addObject("showEditEmployeeModal", true);

            return modelAndView;
        }

        try {
            this.employeeService.update(employeeDTO);
            redirectAttributes.addFlashAttribute("message", "Cập nhật nhân viên thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật nhân viên: " + e.getMessage());
        }

        return new ModelAndView("redirect:/Admin/employee-manager");
    }

    @PostMapping("/employee-manager/disable")
    public ResponseEntity<?> toggleEmployeeStatus(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> employeeIds = request.get("employeeIds");

        if (employeeIds == null || employeeIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Không có nhân viên nào được chọn"));
        }

        try {
            List<Employee> employees = employeeService.findByIds(employeeIds);
            for (Employee emp : employees) {
                emp.setIsDisabled(!emp.getIsDisabled()); // Toggle status
            }
            employeeService.saveAll(employees);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cập nhật trạng thái nhân viên thành công");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Lỗi: " + e.getMessage()));
        }
    }

    @PostMapping("/employee-position/create-form")
    public String createEmployeePosition(@ModelAttribute("employeePosition") EmployeePosition employeePosition,
//                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("positionErrors", bindingResult.getAllErrors());
//            redirectAttributes.addFlashAttribute("showPositionModal", true);
//            return "redirect:/Admin/employee-manager";
//        }

        try {
            employeePositionService.save(employeePosition);
            redirectAttributes.addFlashAttribute("message", "Thêm chức vụ thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm chức vụ: " + e.getMessage());
        }
        return "redirect:/Admin/employee-manager";
    }

    @GetMapping("/employee-position/get-all")
    @ResponseBody
    public ResponseEntity<List<EmployeePosition>> getAllPositions() {
        List<EmployeePosition> positions = employeePositionService.getEmployeePositions();
        return ResponseEntity.ok(positions);
    }
}