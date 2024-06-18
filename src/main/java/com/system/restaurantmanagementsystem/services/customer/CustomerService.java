package com.system.restaurantmanagementsystem.services.customer;

import com.system.restaurantmanagementsystem.dtos.CategoryDto;
import com.system.restaurantmanagementsystem.dtos.ProductDto;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;
import com.system.restaurantmanagementsystem.entities.Category;
import com.system.restaurantmanagementsystem.entities.Product;
import com.system.restaurantmanagementsystem.entities.Reservation;
import com.system.restaurantmanagementsystem.entities.User;
import com.system.restaurantmanagementsystem.enums.ReservationStatus;
import com.system.restaurantmanagementsystem.repositories.CategoryRepository;
import com.system.restaurantmanagementsystem.repositories.IUserSQLRepository;
import com.system.restaurantmanagementsystem.repositories.ProductRepository;
import com.system.restaurantmanagementsystem.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;

    private final IUserSQLRepository userRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto postReservation(ReservationDto dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getCustomerId());
        if (optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setTableType(dto.getTableType());
            reservation.setDateTime(dto.getDateTime());
            reservation.setDescription(dto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            Reservation postedReservation = reservationRepository.save(reservation);
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setId(postedReservation.getId());
            return reservationDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByUser(Long customerId) {
        return reservationRepository.findAllByUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
