package com.example.ecomProj.mappers;

import com.example.ecomProj.dtos.ProductRequestDto;
import com.example.ecomProj.dtos.ProductResponseDto;
import com.example.ecomProj.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toProductResponse(Product product);

    Product toProductEntity(ProductRequestDto productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductRequestDto dto, @MappingTarget Product product);
}
