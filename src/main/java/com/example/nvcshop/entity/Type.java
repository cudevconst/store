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
@Table(name = "type")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String nameType;

    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date lastModify;

    @ManyToMany(mappedBy = "types")
    private Collection<Product> products;
}
