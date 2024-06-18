package com.system.restaurantmanagementsystem.services.admin;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import com.system.restaurantmanagementsystem.dtos.ProductDto;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;
import com.system.restaurantmanagementsystem.entities.Category;
import com.system.restaurantmanagementsystem.entities.Product;
import com.system.restaurantmanagementsystem.entities.Reservation;
import com.system.restaurantmanagementsystem.enums.ReservationStatus;
import com.system.restaurantmanagementsystem.repositories.CategoryRepository;
import com.system.restaurantmanagementsystem.repositories.ProductRepository;
import com.system.restaurantmanagementsystem.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final ReservationRepository reservationRepository;

    public AdminServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ReservationRepository reservationRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public CategoryDto postCategory(CategoryDto dto) throws IOException {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setImg(dto.getImg().getBytes());
        Category created = categoryRepository.save(category);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(created.getId());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }


    //Products Operations
    @Override
    public ProductDto postProduct(Long categoryId, ProductDto dto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(dto,product);
            product.setImg(dto.getImg().getBytes());
            product.setCategory(optionalCategory.get());
            Product update = productRepository.save(product);
            ProductDto productDto = new ProductDto();
            productDto.setId(update.getId());
            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId,String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalArgumentException("Product with id: " + productId + " not found");
        }
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(Product::getProductDto).orElse(null);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto dto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            if (dto.getImg() != null){
                product.setImg(dto.getImg().getBytes());
            }
            Product product1 = productRepository.save(product);
            ProductDto productDto = new ProductDto();
            productDto.setId(product1.getId());
            return productDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservations() {
        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto changeReservationStatus(Long reservationId, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()){
            Reservation existing = optionalReservation.get();
            if(Objects.equals(status,"Approved")){
                existing.setReservationStatus(ReservationStatus.APPROVED);
            }else {
                existing.setReservationStatus(ReservationStatus.DISAPPROVED);
            }
            Reservation updateReservation = reservationRepository.save(existing);
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setId(updateReservation.getId());
            return reservationDto;
        }
        return null;
    }

}
