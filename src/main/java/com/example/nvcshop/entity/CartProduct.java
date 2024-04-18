package com.example.nvcshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProduct {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private Integer quantity;
    private String size;
    private String color;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
