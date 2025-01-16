package com.example.demo.bean.dto.request.update;

import com.example.demo.bean.entity.Product;
import lombok.Data;

@Data
public class UpdateProductRequest {

    private String title;
    private String name;
    private String description;
    private Integer price;

    public Product toEntity(Product product) {
        if (title != null && !title.isEmpty()) {
            product.setTitle(title);
        }
        if (name != null && !name.isEmpty()) {
            product.setName(name);
        }
        if (description != null && !description.isEmpty()) {
            product.setDescription(description);
        }
        if (price != null) {
            product.setPrice(price);
        }
        return product;
    }
}
