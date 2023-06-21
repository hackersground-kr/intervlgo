package com.intervlgo.ourfolio.controller;

import com.intervlgo.ourfolio.dto.UserDto;
import com.intervlgo.ourfolio.dto.UserIdPasswordDto;
import com.intervlgo.ourfolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("")
    public ResponseEntity<Page<UserDto>> getUsers(
            Pageable pageable,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "occupation", required = false) String occupation
    ) {
        return userService.findUsers(pageable, username, region, occupation);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "userId") String userId) {
        return userService.findUserByUserId(userId);
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
