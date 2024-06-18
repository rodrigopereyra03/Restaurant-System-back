package com.system.restaurantmanagementsystem.repositories;

import com.system.restaurantmanagementsystem.enums.UserRole;
import com.system.restaurantmanagementsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSQLRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole admin);
}
