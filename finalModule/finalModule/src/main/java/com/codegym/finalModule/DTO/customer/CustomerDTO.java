package com.codegym.finalModule.DTO.customer;

import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.vatidator.customer.DobConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Integer customerId;
    @NotBlank(message = "Tên không được để trống !")
    @Size(min = 5 , message = "Tên tối thiểu là 5 kí tự !")
    private String customerName ;
    @Pattern(regexp = "^(0[3|5|7|8|9])[0-9]{8}$" ,
            message = "Không đúng định dạng ! (Bắt đầu bằng 03,05,07,09 ,Và phải đủ 10 số)")
    private String phoneNumber;
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày sinh không được để trống !")
    @DobConstraint(min = 15 , message = "Không đủ điều kiện , tuổi phải lớn hơn 15")
    private LocalDate birthDate;
    @Pattern(regexp = "^[a-z0-9]+@gmail\\.com$" , message = "Email không đúng định dạng !")
    private String email;

    public CustomerDTO(Customer customer) {
    }

//    @Override
//    public String toString() {
//        return "CustomerDTO{" +
//                "id=" + id +
//                ", fullName='" + fullName + '\'' +
//                ", phone='" + phone + '\'' +
//                ", address='" + address + '\'' +
//                ", birthDate=" + birthDate +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
