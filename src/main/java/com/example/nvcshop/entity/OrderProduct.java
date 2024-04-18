package com.example.nvcshop.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_product")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProduct {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private Integer quantity;

    private String size;

    private String color;
    @CreatedDate
    private Date bookingDate;

    @LastModifiedDate
    private Date lastModify;

    @ManyToOne
    @JoinColumn(name = "oder_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
