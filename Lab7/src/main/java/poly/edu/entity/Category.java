package poly.edu.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
