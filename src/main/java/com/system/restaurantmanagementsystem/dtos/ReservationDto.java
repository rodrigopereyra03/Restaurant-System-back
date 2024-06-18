package com.system.restaurantmanagementsystem.dtos;

import com.system.restaurantmanagementsystem.enums.ReservationStatus;
import lombok.Data;

import java.util.Date;
@Data
public class ReservationDto {

    private Long id;

    private String tableType;

    private String description;

    private Date dateTime;

    private ReservationStatus reservationStatus;

    private Long customerId;

    private String customerName;
}
