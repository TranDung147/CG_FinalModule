package controllers.cg_finalmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @NotNull(message = "Ngày đặt hàng không được để trống")
    private Instant orderDate;

    @NotNull(message = "Tổng tiền không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tổng tiền phải lớn hơn 0")
    private BigDecimal totalAmount;

    @NotNull(message = "Trạng thái đơn hàng không được để trống")
    private Boolean status; // TRUE = Đã hoàn thành, FALSE = Chưa hoàn thành

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Mỗi order chỉ thuộc về 1 user
}


