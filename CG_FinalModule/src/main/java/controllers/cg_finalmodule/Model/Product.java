package controllers.cg_finalmodule.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 50, message = "Tên sản phẩm tối đa 50 ký tự")
    private String productName;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá không thể âm")
    private Double price;

    @NotBlank(message = "CPU không được để trống")
    @Size(max = 100, message = "CPU tối đa 100 ký tự")
    private String CPU;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không thể âm")
    @Max(value = 99999, message = "Số lượng không thể vượt quá 5 chữ số")
    private Integer quantity;

    @Lob  // Sử dụng kiểu dữ liệu TEXT trong database
    @Column(columnDefinition = "TEXT")
    private String image;

    @NotNull(message = "Kích thước màn hình không được để trống")
    @Digits(integer = 2, fraction = 1, message = "Kích thước màn hình không hợp lệ")
    private Double screenSize;

    @NotNull(message = "Camera không được để trống")
    @Digits(integer = 2, fraction = 1, message = "Độ phân giải camera không hợp lệ")
    private Double camera;

    @NotNull(message = "Selfie camera không được để trống")
    @Digits(integer = 2, fraction = 1, message = "Độ phân giải camera trước không hợp lệ")
    private Double selfie;

    @Lob
    private String description;

    private Instant createdAt;
    private Instant updatedAt;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetail productDetail;
}
