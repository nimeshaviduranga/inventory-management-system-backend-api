package com.inventory.product.service;

import com.inventory.product.dto.ProductDTO;
import com.inventory.product.dto.ProductRequest;
import com.inventory.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long id);
    ProductResponse getProductBySku(String sku);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(Long categoryId);
    List<ProductResponse> searchProducts(String keyword);
    ProductResponse updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
    boolean isSkuExists(String sku);
}