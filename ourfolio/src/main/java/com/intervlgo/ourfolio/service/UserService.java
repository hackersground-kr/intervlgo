package com.intervlgo.ourfolio.service;

import com.intervlgo.ourfolio.auth.PrincipalDetails;
import com.intervlgo.ourfolio.auth.PrincipalDetailsService;
import com.intervlgo.ourfolio.dto.UserDto;
import com.intervlgo.ourfolio.entity.User;
import com.intervlgo.ourfolio.filter.JwtProvider;
import com.intervlgo.ourfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final PrincipalDetailsService principalDetailsService;

    public ResponseEntity<UserDto> signUp(UserDto request) {
        HttpStatus status = HttpStatus.OK;

        Optional<User> optionalUser = userRepository.findByUserId(request.getUserId());
        if (optionalUser.isPresent()){
            status = HttpStatus.CONFLICT;
            return new ResponseEntity<>(status);
        }
        User user = User.builder()
                .userId(request.getUserId())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .username(request.getUsername())
                .region(request.getRegion())
                .occupation(request.getOccupation())
                .build();
        userRepository.save(user);

        UserDto body = user.toDto();
        body.setUserPassword(request.getUserPassword());

        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity<UserDto> signIn(UserDto request) {
        HttpStatus status = HttpStatus.OK;

        PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(request.getUserId());

        if (!passwordEncoder.matches(request.getUserPassword(), principalDetails.getPassword())){
            status = HttpStatus.UNAUTHORIZED;
            return new ResponseEntity<>(status);
        }
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principalDetails.getUsername(),principalDetails.getPassword(),principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtProvider.generateToken(request.getUserId()));

        UserDto body = userRepository.findByUserId(request.getUserId()).get().toDto();
        body.setUserPassword(request.getUserPassword());

        return new ResponseEntity<>(body, headers, status);
    }


}
