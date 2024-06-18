package com.system.restaurantmanagementsystem.services.customer;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import com.system.restaurantmanagementsystem.dtos.ProductDto;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;

import java.util.List;

public interface ICustomerService {
    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoriesByName(String title);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);

    ReservationDto postReservation(ReservationDto dto);

    List<ReservationDto> getReservationsByUser(Long customerId);
}
