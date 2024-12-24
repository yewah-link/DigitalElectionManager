package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.model.entity.Users;
import com.election.UserManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceSecurity {

    private final UserRepository repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Password encoding

    // Register method to save the user
    public GenericResponseV2<Users> register(Users user) {
        try {
            // 1. Check if the user already exists by username (optional but recommended for signup flow)
            if (repo.existsByUsername(user.getUsername())) {
                return GenericResponseV2.<Users>builder()
                        .status(ResponseStatusEnum.ERROR)
                        .message("Username already exists")
                        ._embedded(null)
                        .build();
            }

            // 2. Encrypt the password before saving the user to the database
            user.setPassword(encoder.encode(user.getPassword()));

            // 3. Save the user to the database
            Users savedUser = repo.save(user);

            // 4. Return success response with saved user data
            return GenericResponseV2.<Users>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("User registered successfully")
                    ._embedded(savedUser)
                    .build();

        } catch (Exception e) {
            // Log the error and return an error response
            e.printStackTrace();
            return GenericResponseV2.<Users>builder()
                    .status(ResponseStatusEnum.ERROR)
                    .message("Unable to register user")
                    ._embedded(null)
                    .build();
        }
    }
}
