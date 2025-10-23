package poly.edu.model;

import lombok.*;
import lombok.Builder.Default;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Staff {
    private String id;
    private String fullname;

    @Default
    private String photo = "avatar.jpg";

    @Default
    private Boolean gender = true;

    
    @Default
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday = new Date();

    @Default
    private Double salary = 5_000_000.0;

    @Default
    private Integer level = 0;
}
