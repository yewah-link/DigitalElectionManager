package com.election.UserManagement.model.dto;

import com.election.UserManagement.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Date;
@Data

public class UserDto {
    private Long userId;

    private String registrationNumber;

    private String username;

    private String email;

    private String password;

    private Date created_at;

    private Date updated_at;

    private Role role;
}
