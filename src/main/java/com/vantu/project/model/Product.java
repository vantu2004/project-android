package com.vantu.project.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private BigDecimal price;
    private int stockQuantity;
    private int soldQuantity;
    
    @Column(columnDefinition = "MEDIUMTEXT")
    private String shortDesc;
    
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;
    
    private String imageUrl;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}