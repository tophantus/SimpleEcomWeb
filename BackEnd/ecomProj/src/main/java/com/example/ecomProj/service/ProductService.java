package com.example.ecomProj.service;

import com.example.ecomProj.dtos.ProductRequestDto;
import com.example.ecomProj.dtos.ProductResponseDto;
import com.example.ecomProj.exception.ResourceAlreadyExistsException;
import com.example.ecomProj.exception.ResourceCannotBeDeletedException;
import com.example.ecomProj.exception.ResourceNotFoundException;
import com.example.ecomProj.mappers.ProductMapper;
import com.example.ecomProj.model.Product;
import com.example.ecomProj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final CloudinaryService cloudinaryService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository repo, CloudinaryService cloudinaryService, ProductMapper mapper) {
        this.repo = repo;
        this.cloudinaryService = cloudinaryService;
        this.productMapper = mapper;
    }

    public List<ProductResponseDto> getAllProducts() {
        return repo.findAll().stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponseDto getProductById(int id) {
        Product product = repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with ID " + id + " not found"));
        return productMapper.toProductResponse(product);
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto, MultipartFile imageFile) {
        Product product = productMapper.toProductEntity(productRequestDto);
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(imageFile);
            product.setImageUrl(imageUrl);
        }
        Product savedProduct = repo.save(product);
        return productMapper.toProductResponse(savedProduct);
    }

    public void updateProductInfo(int id, ProductRequestDto productRequestDto) {
        Product existProduct = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        productMapper.updateProductFromDto(productRequestDto, existProduct);
//        if (updatedProductData.getName() != null) {
//            existProduct.setName(updatedProductData.getName());
//        }
//        if (updatedProductData.getDescription() != null) {
//            existProduct.setDescription(updatedProductData.getDescription());
//        }
//        if (updatedProductData.getBrand() != null) {
//            existProduct.setBrand(updatedProductData.getBrand());
//        }
//        if (updatedProductData.getPrice() != null) {
//            existProduct.setPrice(updatedProductData.getPrice());
//        }
//        if (updatedProductData.getCategory() != null) {
//            existProduct.setCategory(updatedProductData.getCategory());
//        }
//        if (updatedProductData.getReleaseDate() != null) {
//            existProduct.setReleaseDate(updatedProductData.getReleaseDate());
//        }
//        if (updatedProductData.getAvailable() != null) {
//            existProduct.setAvailable(updatedProductData.getAvailable());
//        }
//        if (updatedProductData.getStockQuantity() != null) {
//            existProduct.setStockQuantity(updatedProductData.getStockQuantity());
//        }
        repo.save(existProduct);
    }

    public void updateProductImage(int id, MultipartFile imageFile) {
        Product product = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        String imageUrl = product.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            cloudinaryService.deleteImage(imageUrl);
        }
        String newImageUrl = cloudinaryService.uploadFile(imageFile);
        product.setImageUrl(newImageUrl);
        repo.save(product);
    }

    public void removeProduct(int id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        try {
            String imageUrl = product.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                cloudinaryService.deleteImage(imageUrl);
            }

            repo.deleteById(id);

        } catch (Exception e) {
            throw new ResourceCannotBeDeletedException("Product with ID " + id + " cannot be deleted");
        }
    }

    public List<ProductResponseDto> searchProducts(String keyword) {
        return repo.searchProducts(keyword).stream().map(productMapper::toProductResponse).toList();
    }
}
