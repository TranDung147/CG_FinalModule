package controllers.cg_finalmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "customer/index";
    }
    @GetMapping("/blank")
    public String blank() {
        return "customer/blank";
    }
    @GetMapping("/checkout")
    public String checkout() {
        return "customer/checkout";
    }
    @GetMapping("/product")
    public String product() {
        return "customer/product";
    }
    @GetMapping("/store")
    public String store() {
        return "customer/store";
    }
}
