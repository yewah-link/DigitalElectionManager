package com.election.UserManagement.service;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.mappers.UserMapper;
import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.model.entity.Users;
import com.election.UserManagement.model.principle.UserPrincipal;
import com.election.UserManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public GenericResponseV2<UserDto> createUser(UserDto userDto) {
        try {
            // 1. Convert UserDto to User entity for purpose of saving in the db
            Users userToBeSaved = userMapper.userDtoToUser(userDto);
            // 2. Save the User entity to the database
            Users savedUser = userRepository.save(userToBeSaved);
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
            Users userFromDb = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
            UserDto response = userMapper.userToUserDto(userFromDb);
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("User retrieved successfully")
                    ._embedded(response)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.ERROR)
                    .message("Unable to retrieve user")
                    ._embedded(null)
                    .build();
        }
    }
                    
    public GenericResponseV2<UserDto> updateById(UserDto userDto) {
        try {
            //Check if the user exists in the database by ID
            Optional<Users> existingUserOpt = userRepository.findById(userDto.getUserId());
            if (!existingUserOpt.isPresent()) {
                // If the user does not exist, return a 404 error with an appropriate message
                return GenericResponseV2.<UserDto>builder()
                        .status(ResponseStatusEnum.ERROR)
                        .message("User not found")
                        ._embedded(null)
                        .build();
            }
            //Convert the UserDto to User entity
            Users userToBeUpdated = userMapper.userDtoToUser(userDto);
            // Save the updated User entity in the database
            Users updatedUser = userRepository.save(userToBeUpdated);

            //Convert the updated User entity back to UserDto for the response
            UserDto response = userMapper.userToUserDto(updatedUser);
            // Return a success response with the updated user data
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.SUCCESS)
                    .message("User updated successfully")
                    ._embedded(response)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response if there was an issue
            return GenericResponseV2.<UserDto>builder()
                    .status(ResponseStatusEnum.ERROR)
                    .message("Unable to update user: " + e.getMessage())
                    ._embedded(null)
                    .build();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);

        if(user == null){
            System.out.println("User Not Found");
            throw  new UsernameNotFoundException("User not found");

        }
        return new UserPrincipal(user);
    }
}
