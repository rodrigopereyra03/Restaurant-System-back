package com.system.restaurantmanagementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {

    private String email;
    private String password;

}
