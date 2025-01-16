package com.example.demo.bean.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Long roleId;

    @Column(columnDefinition = "TINYINT")
    private Boolean isDelete = false;
}
