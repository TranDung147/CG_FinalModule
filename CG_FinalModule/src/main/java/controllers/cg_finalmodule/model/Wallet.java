package controllers.cg_finalmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {
    @Id
    @Column(name = "user_id")
    private Integer userId; // Sử dụng UserID làm khóa chính

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Số dư không được để trống")
    @DecimalMin(value = "0.0", message = "Số dư không thể âm")
    private BigDecimal money;

    @NotNull(message = "Trạng thái ví không được để trống")
    private Boolean status; // true = hoạt động, false = bị khóa
}
