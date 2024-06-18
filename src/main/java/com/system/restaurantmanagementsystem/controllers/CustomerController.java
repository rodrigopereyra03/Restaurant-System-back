package com.system.restaurantmanagementsystem.controllers;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import com.system.restaurantmanagementsystem.dtos.ProductDto;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;
import com.system.restaurantmanagementsystem.services.customer.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = service.getAllCategories();
        if (categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByName(@PathVariable String title){
        List<CategoryDto> categoryDtoList = service.getCategoriesByName(title);
        if (categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = service.getProductsByCategory(categoryId);
        if (productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId,@PathVariable String title){
        List<ProductDto> productDtoList = service.getProductsByCategoryAndTitle(categoryId,title);
        if (productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto dto) throws IOException {
        ReservationDto reservationDto = service.postReservation(dto);
        if(reservationDto==null) return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto);
    }

    @GetMapping("/reservations/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable Long customerId){
        List<ReservationDto> reservationDtoList = service.getReservationsByUser(customerId);
        if (reservationDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtoList);
    }
}
