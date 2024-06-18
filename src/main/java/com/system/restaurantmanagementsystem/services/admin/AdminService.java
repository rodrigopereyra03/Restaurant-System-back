package com.system.restaurantmanagementsystem.services.admin;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import com.system.restaurantmanagementsystem.dtos.ProductDto;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    CategoryDto postCategory(CategoryDto dto) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryId, ProductDto dto) throws IOException;

    List<ProductDto> getAllProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId,String title);

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto dto) throws IOException;

    List<ReservationDto> getReservations();

    ReservationDto changeReservationStatus(Long reservationId, String status);
}
