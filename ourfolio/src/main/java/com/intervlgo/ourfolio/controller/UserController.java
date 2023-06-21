package com.intervlgo.ourfolio.controller;

import com.intervlgo.ourfolio.dto.UserDto;
import com.intervlgo.ourfolio.dto.UserIdPasswordDto;
import com.intervlgo.ourfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserDto> signUp(
            @RequestBody UserDto userDto
    ) {
        return userService.signUp(userDto);
    }

    @PostMapping("/token")
    public ResponseEntity<UserDto> signIn(
            @RequestBody UserDto userDto
    ) {
        return userService.signIn(userDto);
    }

    @PutMapping("")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserDto userDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        return userService.updateUser(userDto, jwtToken);
    }

    @PutMapping("/auth")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserIdPasswordDto userIdPasswordDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        return userService.updateIdPassword(userIdPasswordDto, jwtToken);
    }





}
