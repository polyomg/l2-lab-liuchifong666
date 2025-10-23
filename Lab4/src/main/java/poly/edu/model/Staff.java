package poly.edu.model;

import lombok.*;
import lombok.Builder.Default;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Staff {
    @NotBlank(message = "Chưa nhập email")
    @Email(message = "Email không hợp lệ")
    private String id;

    @NotBlank(message = "Chưa nhập họ tên")
    private String fullname;

    @Default
    private String photo = "photo.jpg";

    @NotNull(message = "Chưa chọn giới tính")
    private Boolean gender;

    @NotNull(message = "Chưa chọn ngày sinh")
    private LocalDate birthday;        

    @NotNull(message = "Chưa nhập lương")
    @Min(value = 1000, message = "Lương tối thiểu là 1000")
    private Double salary;

    @Default
    private Integer level = 0;      
}
