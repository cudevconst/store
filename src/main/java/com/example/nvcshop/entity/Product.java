package com.example.nvcshop.entity;

import com.example.nvcshop.utils.Util;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String slug;

    private Double price;
    private String image1;
    private String image2;
    private String image3;

    @ElementCollection
    private Set<String> sizes = new HashSet<>();

    @ElementCollection
    private Set<String> color = new HashSet<>();

    @PrePersist
    private void generateSlug() {
        if (StringUtils.isEmpty(this.slug)) {
            this.slug = Util.toSlug(name) + "-" + Util.getRandomNumber();
        }
    }
    @PreUpdate
    private void generateSlugUpdate(){
        this.slug = Util.toSlug(name) + "-" + Util.getRandomNumber();
    }
    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date lastModify;

    @ManyToMany
    @JoinTable(
            name = "type_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> types;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

}
