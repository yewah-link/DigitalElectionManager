package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.model.dto.UserDto;

public interface UserService {
    GenericResponseV2<UserDto> createUser(UserDto userDto);
}
