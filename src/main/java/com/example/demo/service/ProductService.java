package com.example.demo.service;

import com.example.demo.bean.dto.request.ProductRequest;
import com.example.demo.bean.dto.request.update.UpdateProductRequest;
import com.example.demo.bean.entity.Product;
import com.example.demo.dao.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll().stream().filter(product -> !product.getIsDelete()).toList();
    }

    public void create(ProductRequest request) {
        Product entity = request.toEntity();
        productRepository.save(entity);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void update(Long id, UpdateProductRequest request) {
        productRepository.findById(id).ifPresent(product -> {
            Product update = request.toEntity(product);
            productRepository.save(update);
        });
    }

    public void delete(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setIsDelete(true);
            productRepository.save(product);
        });
    }
}
