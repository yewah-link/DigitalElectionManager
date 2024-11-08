package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.model.dto.UserDto;

import java.util.List;

public interface UserService {
    GenericResponseV2<UserDto> createUser(UserDto userDto);
    GenericResponseV2<List<UserDto>> getAllUsers();

    GenericResponseV2<UserDto> getUserById(Long userId);
    GenericResponseV2<UserDto> updateById(UserDto userDto);
}
