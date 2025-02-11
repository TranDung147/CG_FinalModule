package controllers.cg_finalmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class HomeController {
    @GetMapping
    public String home() {
        return "index";
    }
    @GetMapping("/blank")
    public String blank() {
        return "blank";
    }
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
    @GetMapping("/product")
    public String product() {
        return "product";
    }
    @GetMapping("/store")
    public String store() {
        return "store";
    }
}
