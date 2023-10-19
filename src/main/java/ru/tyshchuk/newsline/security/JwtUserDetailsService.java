package ru.tyshchuk.newsline.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.security.jwt.JwtUser;
import ru.tyshchuk.newsline.security.jwt.JwtUserFactory;
import ru.tyshchuk.newsline.services.UserService;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        try {
            JwtUser jwtUser = JwtUserFactory.create(user);
            log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
            return jwtUser;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
