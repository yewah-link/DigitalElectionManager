package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.mappers.UserMapper;
import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.User;
import com.election.UserManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public GenericResponseV2<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
            return GenericResponseV2.<List<UserDto>>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("Users retrieved successfully")
                    ._embedded(users)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return GenericResponseV2.<List<UserDto>>builder()
                    .status(ResponseStatusEnum.ERROR)
                    .message("Unable to retrieve users")
                    ._embedded(null)
                    .build();

        }
    }

    @Override
    public GenericResponseV2<UserDto> getUserById(Long userId) {
        try {
            User userFromDb = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
            UserDto response = userMapper.userToUserDto(userFromDb);
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("User retrieved successfully")
                    ._embedded(response)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.ERROR)
                    .message("Unable to retrieve user")
                    ._embedded(null)
                    .build();
        }
    }
}