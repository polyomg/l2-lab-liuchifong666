package poly.edu.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate")
    private Date createDate = new Date();

    @Column(name = "Available")
    private Boolean available = true;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}
