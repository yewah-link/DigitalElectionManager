package com.election.UserManagement.mappers;

import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserMapperDecorator implements UserMapper{

    @Autowired
    @Qualifier("delegate")
    private UserMapper userMapper;

    @Override
    public UserDto userToUserDto(Users user) {
        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;
    }

    @Override
    public Users userDtoToUser(UserDto userDto) {
        Users user = userMapper.userDtoToUser(userDto);
        return user;
    }
}