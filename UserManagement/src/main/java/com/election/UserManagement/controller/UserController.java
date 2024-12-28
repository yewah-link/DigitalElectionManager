package com.election.UserManagement.controller;

import com.election.UserManagement.common.GenericResponseV2;
import com.election.UserManagement.common.ResponseStatusEnum;
import com.election.UserManagement.model.dto.UserDto;
import com.election.UserManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<GenericResponseV2<UserDto>> registerUser (@RequestBody UserDto userDto){
        GenericResponseV2<UserDto> response = userService.register(userDto);
        if(response.getStatus() == ResponseStatusEnum.SUCCESS){
            return ResponseEntity.ok().body(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }


    }
    @PostMapping
    public ResponseEntity<GenericResponseV2<UserDto>> createdUser(@RequestBody UserDto userDto){
        GenericResponseV2<UserDto> response = userService.createUser(userDto);
        if (response.getStatus().equals(ResponseStatusEnum.SUCCESS)){
            return ResponseEntity.ok().body(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping
    public ResponseEntity<GenericResponseV2<List<UserDto>>> getAllUsers() {
        GenericResponseV2<List<UserDto>> response = userService.getAllUsers();
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


    @GetMapping("/user-id")
    public ResponseEntity<GenericResponseV2<UserDto>> getUserById(
            @RequestParam(name = "userId")Long userId
    ){
        GenericResponseV2<UserDto> responseV2 = userService.getUserById(userId);
        if (responseV2.getStatus().equals(ResponseStatusEnum.SUCCESS)){
            return ResponseEntity.ok().body(responseV2);
        }else {
            return ResponseEntity.badRequest().body(responseV2);
        }
    }


}
