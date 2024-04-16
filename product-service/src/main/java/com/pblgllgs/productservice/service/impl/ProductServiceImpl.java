package com.pblgllgs.productservice.service.impl;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.productservice.dto.ProductRequest;
import com.pblgllgs.productservice.dto.ProductResponse;
import com.pblgllgs.productservice.entity.Product;
import com.pblgllgs.productservice.exception.ProductServiceException;
import com.pblgllgs.productservice.repository.ProductRepository;
import com.pblgllgs.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Long createProduct(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    @Override
    public ProductResponse findById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductServiceException("Product not found", HttpStatus.NOT_FOUND)
        );
        return new ProductResponse(
                product.getProductName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    @Override
    public void reduceQuantity(Long productId, int quantity) {
        log.info("Reduce quantity: {}", quantity);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductServiceException("Product not found", HttpStatus.NOT_FOUND)
        );
        if (product.getQuantity() < quantity) {
            log.error("Reduce quantity fail, not enough product, quantity request {} and found: {}", quantity, product.getQuantity());
            throw new ProductServiceException("Product not enough", HttpStatus.BAD_REQUEST);
        }
        log.info("Reduce quantity success");
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
