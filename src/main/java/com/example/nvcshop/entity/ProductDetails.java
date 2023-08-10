package com.example.nvcshop.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class ProductDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private Double price;
    private String color;
    private String size;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date lastModify;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @OneToOne(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

//    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
//    private Collection<Cart> carts;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderProduct> orderProducts;


}
