package com.intervlgo.ourfolio.controller;

import com.intervlgo.ourfolio.dto.UserDto;
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

}
