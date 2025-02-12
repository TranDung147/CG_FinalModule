package controllers.cg_finalmodule.controller;


import controllers.cg_finalmodule.model.User;
import controllers.cg_finalmodule.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Admin")
public class UserController {
    @Autowired
    private IUserService iuserService;

    @GetMapping
    public String EmployeeList(
            Model model,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "type", required = false, defaultValue = "all") String type,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo) {


        Page<User> list = iuserService.searchUsers(keyword, type, pageNo);

        // Đưa dữ liệu vào model
        model.addAttribute("list", list.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "ADMINBASE/EmployeeList"; // Trả về trang giao diện
    }
}
