package com.election.UserManagement.mappers;

import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.Users;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@DecoratedWith(value = UserMapperDecorator.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    Users userDtoToUser(UserDto userDto);
    UserDto userToUserDto(Users user);
}
