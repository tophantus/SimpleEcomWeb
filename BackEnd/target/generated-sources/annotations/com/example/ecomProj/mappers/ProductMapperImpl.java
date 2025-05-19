package com.example.ecomProj.mappers;

import com.example.ecomProj.dtos.ProductRequestDto;
import com.example.ecomProj.dtos.ProductResponseDto;
import com.example.ecomProj.model.Product;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-27T10:50:00+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDto toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        int id = 0;
        String name = null;
        String description = null;
        String brand = null;
        BigDecimal price = null;
        String category = null;
        Date releaseDate = null;
        Boolean available = null;
        Integer stockQuantity = null;
        String imageUrl = null;

        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        brand = product.getBrand();
        price = product.getPrice();
        category = product.getCategory();
        releaseDate = product.getReleaseDate();
        available = product.getAvailable();
        stockQuantity = product.getStockQuantity();
        imageUrl = product.getImageUrl();

        ProductResponseDto productResponseDto = new ProductResponseDto( id, name, description, brand, price, category, releaseDate, available, stockQuantity, imageUrl );

        return productResponseDto;
    }

    @Override
    public Product toProductEntity(ProductRequestDto productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productRequest.getName() );
        product.description( productRequest.getDescription() );
        product.brand( productRequest.getBrand() );
        product.price( productRequest.getPrice() );
        product.category( productRequest.getCategory() );
        product.releaseDate( productRequest.getReleaseDate() );
        product.available( productRequest.getAvailable() );
        product.stockQuantity( productRequest.getStockQuantity() );
        product.imageUrl( productRequest.getImageUrl() );

        return product.build();
    }

    @Override
    public void updateProductFromDto(ProductRequestDto dto, Product product) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            product.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            product.setDescription( dto.getDescription() );
        }
        if ( dto.getBrand() != null ) {
            product.setBrand( dto.getBrand() );
        }
        if ( dto.getPrice() != null ) {
            product.setPrice( dto.getPrice() );
        }
        if ( dto.getCategory() != null ) {
            product.setCategory( dto.getCategory() );
        }
        if ( dto.getReleaseDate() != null ) {
            product.setReleaseDate( dto.getReleaseDate() );
        }
        if ( dto.getAvailable() != null ) {
            product.setAvailable( dto.getAvailable() );
        }
        if ( dto.getStockQuantity() != null ) {
            product.setStockQuantity( dto.getStockQuantity() );
        }
        if ( dto.getImageUrl() != null ) {
            product.setImageUrl( dto.getImageUrl() );
        }
    }
}
