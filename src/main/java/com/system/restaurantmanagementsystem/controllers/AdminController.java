package com.system.restaurantmanagementsystem.controllers;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import com.system.restaurantmanagementsystem.dtos.ProductDto;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;
import com.system.restaurantmanagementsystem.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }


    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@ModelAttribute CategoryDto dto) throws IOException {
       CategoryDto categoryDto = service.postCategory(dto);
       if(categoryDto==null)return ResponseEntity.notFound().build();
       return ResponseEntity.ok(categoryDto);
    }


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = service.getAllCategories();
        if (categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title){
        List<CategoryDto> categoryDtoList = service.getAllCategoriesByTitle(title);
        if (categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    //Products operations

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@PathVariable Long categoryId, @ModelAttribute ProductDto dto) throws IOException {
        ProductDto productDto = service.postProduct(categoryId,dto);
        if(productDto==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = service.getAllProductsByCategory(categoryId);
        if (productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId,@PathVariable String title){
        List<ProductDto> productDtoList = service.getProductsByCategoryAndTitle(categoryId,title);
        if (productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        ProductDto productDto = service.getProductById(productId);
        if (productDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto dto) throws IOException {
        ProductDto productDto = service.updateProduct(productId,dto);
        if(productDto==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations(){
        List<ReservationDto> reservationDtoList = service.getReservations();
        if (reservationDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtoList);
    }

    @GetMapping("/reservation/{reservationId}/{status}")
    public ResponseEntity<ReservationDto> changeReservationStatus(@PathVariable Long reservationId, @PathVariable String status){
        ReservationDto reservationDto = service.changeReservationStatus(reservationId,status);
        if (reservationDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDto);
    }

}
