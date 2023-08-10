package com.example.nvcshop.entity;

import com.example.nvcshop.utils.Util;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<TypeProduct> typeProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductDetails> productDetails;



}
