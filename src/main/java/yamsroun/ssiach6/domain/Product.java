package yamsroun.ssiach6.domain;

import jakarta.persistence.*;
import lombok.*;
import yamsroun.ssiach6.data.Currency;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    private String name;
    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
