package com.election.UserManagement.model.entity;

import com.election.UserManagement.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true)
    private String registrationNumber;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private Date created_at;

    private Date updated_at;
    @Enumerated(EnumType.STRING)
    private Role role;
}
