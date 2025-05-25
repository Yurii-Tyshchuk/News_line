package ru.tyshchuk.newsline.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.dto.user.RequestUser;
import ru.tyshchuk.newsline.security.jwt.JwtTokenProvider;
import ru.tyshchuk.newsline.services.UserService;

@RestController()
@RequestMapping("/api/profile")
@Slf4j
public class ProfileController {
    private final UserService userService;
    private final JwtTokenProvider provider;

    public ProfileController(UserService userService, JwtTokenProvider provider) {
        this.userService = userService;
        this.provider = provider;
    }

    @RequestMapping(value = "/getProfileInfo", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            if (token == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            String username = this.provider.getUsername(token.substring(7, token.length()));
            User user = this.userService.findByUsername(username);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody RequestUser user) {
        try {
            this.userService.register(user);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
