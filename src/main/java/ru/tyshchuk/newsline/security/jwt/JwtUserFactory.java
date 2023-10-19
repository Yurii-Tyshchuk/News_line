package ru.tyshchuk.newsline.security.jwt;

import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.domain.UserRole;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getSecondName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRole::getRole).toList(),
                user.getRoles().stream().map(userRole -> userRole.getRole().getRole()).toList());
    }
}
