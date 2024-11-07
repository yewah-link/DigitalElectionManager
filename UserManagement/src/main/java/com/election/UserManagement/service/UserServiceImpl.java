package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.mappers.UserMapper;
import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.User;
import com.election.UserManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public GenericResponseV2<UserDto> createUser(UserDto userDto) {
        try {
            // 1. Convert UserDto to User entity for purpose of saving in the db
            User userToBeSaved = userMapper.userDtoToUser(userDto);
            // 2. Save the User entity to the database
            User savedUser = userRepository.save(userToBeSaved);
            // 3. Convert the saved User entity back to UserDto for response
            UserDto response = userMapper.userToUserDto(savedUser);

            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("user created successfully")
                    ._embedded(response)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.ERROR)
                    .message("unable to create user")
                    ._embedded(null)
                    .build();
        }
    }
}