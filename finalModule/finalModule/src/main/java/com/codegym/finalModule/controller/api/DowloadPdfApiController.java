package com.codegym.finalModule.controller.api;


import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.service.common.PDFService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/download")
public class DowloadPdfApiController {
    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadInvoicePdf(@RequestBody OrderDTO orderDTO) {
        PDFService pdfService = new PDFService();
        byte[] pdf = pdfService.createInvoicePDF(orderDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=invoice_" + orderDTO.getCustomerId() + ".pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

}
