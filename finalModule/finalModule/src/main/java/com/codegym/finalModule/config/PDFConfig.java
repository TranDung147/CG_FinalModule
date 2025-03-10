package com.codegym.finalModule.config;

import com.codegym.finalModule.service.common.PDFService;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Configuration
public class PDFConfig {

    @Bean
    public PdfWriter pdfWriter() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        return new PdfWriter(byteArrayOutputStream);
    }
}
