package ru.tyshchuk.newsline.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.tyshchuk.newsline.domain.*;
import ru.tyshchuk.newsline.domain.embeddings.UserRoleKey;
import ru.tyshchuk.newsline.repository.RoleRepository;
import ru.tyshchuk.newsline.repository.UserRepository;
import ru.tyshchuk.newsline.repository.UserRoleRepository;

@Component
@AllArgsConstructor
public class CommandLineAppStartupRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public void runUser() {
        if (userRepository.findByUsername("Ad") == null) {
            User user = new User("Ad", "Ad", "Ad", passwordEncoder.encode("12"), "qwe@mail.ru");
            Role role = new Role();
            role.setRole(Roles.ROLE_ADMIN);
            UserRole userRole = new UserRole(user, role);
            role = roleRepository.save(role);
            user = userRepository.save(user);

            userRole.setId(new UserRoleKey(user.getId(), role.getId()));
            userRoleRepository.save(userRole);
        }
    }
}
