package com.system.restaurantmanagementsystem.mappers;

import com.system.restaurantmanagementsystem.dtos.UserDto;
import com.system.restaurantmanagementsystem.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User dtoToUser(UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
