package com.pblgllgs.productservice.service;
/*
 *
 * @author pblgl
 * Created on 15-04-2024
 *
 */

import com.pblgllgs.productservice.dto.ProductRequest;
import com.pblgllgs.productservice.dto.ProductResponse;

public interface ProductService {
    Long createProduct(ProductRequest productRequest);

    ProductResponse findById(Long productId);

    void reduceQuantity(Long productId, int quantity);
}
