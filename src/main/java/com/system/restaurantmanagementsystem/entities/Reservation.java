package com.system.restaurantmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.restaurantmanagementsystem.dtos.ReservationDto;
import com.system.restaurantmanagementsystem.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableType;

    private String description;

    private Date dateTime;

    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public ReservationDto getReservationDto() {
        ReservationDto dto = new ReservationDto();
        dto.setId(id);
        dto.setTableType(tableType);
        dto.setDescription(description);
        dto.setDateTime(dateTime);
        dto.setReservationStatus(reservationStatus);
        dto.setCustomerId(user.getId());
        dto.setCustomerName(user.getName());
        return dto;
    }
}
