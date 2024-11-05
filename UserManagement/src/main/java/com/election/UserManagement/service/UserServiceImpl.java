package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.User;
import com.election.UserManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public GenericResponseV2<UserDto> createUser(UserDto userDto) {
        try {
            User userToBeSaved = new User();
            userToBeSaved.setId(userDto.getId());
            userToBeSaved.setRegistrationNumber(userDto.getRegistrationNumber());
            userToBeSaved.setName(userDto.getName());
            userToBeSaved.setEmail(userDto.getEmail());
            userToBeSaved.setPassword(userDto.getPassword());
            userToBeSaved.setRole(userDto.getRole());
            userToBeSaved.setCreated_at(userDto.getCreated_at());
            userToBeSaved.setUpdated_at(userDto.getUpdated_at());

            User savedUser = userRepository.save(userToBeSaved);
            UserDto responseDto = new UserDto();
            responseDto.setId(savedUser.getId());
            responseDto.setRegistrationNumber(savedUser.getRegistrationNumber());
            responseDto.setName(savedUser.getName());
            responseDto.setEmail(savedUser.getEmail());
            responseDto.setRole(savedUser.getRole());
            responseDto.setCreated_at(savedUser.getCreated_at());
            responseDto.setUpdated_at(savedUser.getUpdated_at());

            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("user created successfully")
                    ._embedded(responseDto)
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
