package com.example.ecomProj.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ProductRequestDto {
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private Boolean available;
    private Integer stockQuantity;
    private String imageUrl;
}
