package controllers.cg_finalmodule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "Tên không được để trống và chứa ít nhất một ký tự !")
    private String name;
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",
            message = "Email không đúng định dạng! (Ví dụ: example@gmail.com)"
    )
    private String email;
    @Pattern(
            regexp = "^(0|84|\\+84)(3|5|7|8|9)[0-9]{8}$",
            message = "Số điện thoại không hợp lệ!"
    )
    private String phone;
    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private Instant createdAt;
    private Instant updatedAt;
}
