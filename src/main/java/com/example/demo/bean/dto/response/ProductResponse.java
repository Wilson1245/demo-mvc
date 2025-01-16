package com.example.demo.bean.dto.response;

import com.example.demo.bean.entity.Product;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String title;
    private String name;
    private String description;
    private Integer price;

    public static ProductResponse of(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        return response;
    }
}
