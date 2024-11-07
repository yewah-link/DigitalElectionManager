package com.election.UserManagement.controller;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericResponseV2<UserDto>> createUser(@RequestBody UserDto userDto){
        GenericResponseV2<UserDto> response = userService.createUser(userDto);
        if (response.getStatus().equals(ResponseStatusEnum.SUCCESS)){
            return ResponseEntity.ok().body(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<GenericResponseV2<UserDto>>updateById(@PathVariable Long userId,@RequestBody UserDto userDto){
        GenericResponseV2<UserDto>response = userService.updateById(userDto);
        if (response.getStatus().equals(ResponseStatusEnum.SUCCESS)){
            return ResponseEntity.ok().body(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }

      
}
