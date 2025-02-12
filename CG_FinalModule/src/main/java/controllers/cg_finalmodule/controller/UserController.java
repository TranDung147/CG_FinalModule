package controllers.cg_finalmodule.controller;

import controllers.cg_finalmodule.model.User;
import controllers.cg_finalmodule.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Controller
@RequestMapping("/Admin")
public class UserController {
    @Autowired
    private IUserService iuserService;

    @GetMapping
    public String employeeList(
            Model model,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "type", required = false, defaultValue = "all") String type,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo) {

        Page<User> list = iuserService.searchUsers(keyword, type, pageNo);

        model.addAttribute("list", list.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "ADMINBASE/EmployeeList";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("user", new User());
        return "ADMINBASE/AddEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "ADMINBASE/AddEmployee";
        }
        if (user.getUserId() == 0) {
            user.setCreateAt(Instant.now());
        }
        user.setUpdateAt(Instant.now());
        iuserService.saveUser(user);
        return "redirect:/Admin";
    }
}
