package com.election.UserManagement.mappers;

import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserMapperDecorator implements UserMapper{

    @Autowired
    @Qualifier("delegate")
    private UserMapper userMapper;

    @Override
    public UserDto userToUserDto(User user) {
        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return user;
    }
}