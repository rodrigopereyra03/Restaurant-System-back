package com.system.restaurantmanagementsystem.entities;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[]img;


    public CategoryDto getCategoryDto(){
        CategoryDto dto = new CategoryDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setReturnedImg(img);
        return dto;
    }
}
