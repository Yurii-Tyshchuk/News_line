package ru.tyshchuk.newsline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.dto.auth.AuthRequest;
import ru.tyshchuk.newsline.dto.auth.RefreshRequest;
import ru.tyshchuk.newsline.security.jwt.JwtTokenProvider;
import ru.tyshchuk.newsline.services.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequest requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles().stream().map(userRole -> userRole.getRole().getRole()).toList());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("last_date", new Date());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("refresh")
    public ResponseEntity refreshToken(@RequestBody RefreshRequest request) {
        try {
            Map<Object, Object> response = new HashMap<>();
            if (this.jwtTokenProvider.validateToken(request.getToken())) {
                String token = this.jwtTokenProvider.refreshToken(request.getToken());
                response.put("new_token", token);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.badRequest().body("Не валидный токен");
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token");
        }
    }

    @GetMapping("/check")
    public ResponseEntity check() {
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
