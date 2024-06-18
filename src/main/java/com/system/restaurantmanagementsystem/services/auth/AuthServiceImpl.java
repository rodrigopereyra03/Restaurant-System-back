package com.system.restaurantmanagementsystem.services.auth;

import com.system.restaurantmanagementsystem.dtos.SingupRequest;
import com.system.restaurantmanagementsystem.dtos.UserDto;
import com.system.restaurantmanagementsystem.enums.UserRole;
import com.system.restaurantmanagementsystem.entities.User;
import com.system.restaurantmanagementsystem.repositories.IUserSQLRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{

    private final IUserSQLRepository userSQLRepository;

    public AuthServiceImpl(IUserSQLRepository userSQLRepository) {
        this.userSQLRepository = userSQLRepository;
    }


    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userSQLRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount==null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userSQLRepository.save(user);
        }
    }

    @Override
    public UserDto createUser(SingupRequest singupRequest) {
        User user = new User();
        user.setName(singupRequest.getName());
        user.setEmail(singupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User created = userSQLRepository.save(user);
        UserDto dto = new UserDto();
        dto.setId(created.getId());
        dto.setName(created.getName());
        dto.setEmail(created.getEmail());
        dto.setUserRole(created.getUserRole());
       // UserMapper.userToDto(userSQLRepository.save(UserMapper.dtoToUser(singupRequest)));
        return dto;
    }
}
