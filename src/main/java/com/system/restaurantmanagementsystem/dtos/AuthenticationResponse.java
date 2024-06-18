package com.system.restaurantmanagementsystem.dtos;

import com.system.restaurantmanagementsystem.enums.UserRole;
import lombok.Data;

@Data

public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}
