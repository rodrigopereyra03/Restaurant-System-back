package com.system.restaurantmanagementsystem.dtos;

import lombok.Data;

@Data
public class SingupRequest {
    private String name;
    private String email;
    private String password;
}
