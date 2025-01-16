package com.example.demo.bean.dto.request;

import com.example.demo.bean.entity.Product;
import lombok.Data;

@Data
public class ProductRequest {

    private String title;
    private String name;
    private String description;
    private Integer price;
//    private Long fileId;

    public Product toEntity() {
        Product product = new Product();
        product.setTitle(title);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
