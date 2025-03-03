package com.codegym.finalModule.controller.admin;


import com.codegym.finalModule.DTO.transaction.InventoryTransactionDTO;
import com.codegym.finalModule.model.InventoryTransaction;
import com.codegym.finalModule.service.impl.InventoryTransactionService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/transactions")
public class InventoryTransactionController {

    private final InventoryTransactionService inventoryTransactionService;
    public InventoryTransactionController(InventoryTransactionService inventoryTransactionService) {
        this.inventoryTransactionService = inventoryTransactionService;
    }

    @PostMapping("/export")
    public ModelAndView exportHandler(@ModelAttribute InventoryTransactionDTO transactionDTO ,
                                      RedirectAttributes redirectAttributes) {
       this.inventoryTransactionService.exportFromWarehouse(transactionDTO);
       redirectAttributes.addFlashAttribute("successfulNotification" ,"Xuất kho thành công !") ;
       return new ModelAndView("redirect:/warehouses");
    }
    @GetMapping("/history")
    public ModelAndView showTransactions (@RequestParam(name = "searchField", required = false, defaultValue = "productName") String field,
                                          @RequestParam(name = "searchInput", required = false, defaultValue = "") String keyword,
                                          @RequestParam(name = "transactionType", required = false) String transactionType,
                                          @RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fromDate,
                                          @RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "4") int size) {

        ModelAndView modelAndView = new ModelAndView("admin/transactions/transactions-table");
        String filterKeyWord = keyword.trim();
        Page<InventoryTransaction> transactions = this.inventoryTransactionService.searchTransactions(field, filterKeyWord, page, size, fromDate,
                toDate, transactionType);

        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("page", page);
        modelAndView.addObject("size", size);
        modelAndView.addObject("transactionType", transactionType);
        modelAndView.addObject("fromDate", fromDate);
        modelAndView.addObject("toDate", toDate);
        modelAndView.addObject("field", field);
        modelAndView.addObject("filterKeyWord", filterKeyWord);

        return modelAndView;

    }
}
