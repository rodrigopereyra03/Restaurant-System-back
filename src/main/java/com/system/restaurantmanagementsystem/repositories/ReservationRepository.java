package com.system.restaurantmanagementsystem.repositories;

import com.system.restaurantmanagementsystem.dtos.ReservationDto;
import com.system.restaurantmanagementsystem.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByUserId(Long customerId);
}
