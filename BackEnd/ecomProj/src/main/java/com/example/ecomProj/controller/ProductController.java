package com.example.ecomProj.controller;


import com.example.ecomProj.dtos.ProductRequestDto;
import com.example.ecomProj.dtos.ProductResponseDto;
import com.example.ecomProj.model.Product;
import com.example.ecomProj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestPart("product") ProductRequestDto productRequestDto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile,
            UriComponentsBuilder uriBuilder
    ) {
        ProductResponseDto createdProduct = service.createProduct(productRequestDto, imageFile);
        URI uri = uriBuilder.path("/api/products/{id}").buildAndExpand(createdProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(createdProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProductInfo(@PathVariable int id, @RequestBody ProductRequestDto productRequest) {
        service.updateProductInfo(id, productRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchProducts(@RequestParam String keyword) {
        List<ProductResponseDto> products = service.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateProductImage(@PathVariable int id, @RequestPart("image") MultipartFile imageFile) {
        service.updateProductImage(id, imageFile);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable int id) {
        service.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}
