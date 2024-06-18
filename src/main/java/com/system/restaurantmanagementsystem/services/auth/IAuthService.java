package com.system.restaurantmanagementsystem.services.auth;

import com.system.restaurantmanagementsystem.dtos.SingupRequest;
import com.system.restaurantmanagementsystem.dtos.UserDto;

public interface IAuthService {
    UserDto createUser(SingupRequest singupRequest);
}
