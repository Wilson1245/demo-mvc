package com.example.demo.bean.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String name;
    private String description;
    private String img;
    private Integer price;
    @Column(columnDefinition = "TINYINT")
    private Boolean isDelete = false;
}
