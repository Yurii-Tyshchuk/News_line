package ru.tyshchuk.newsline.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.tyshchuk.newsline.domain.Role;
import ru.tyshchuk.newsline.domain.Roles;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.domain.UserRole;
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
            Role adminRole = new Role(Roles.ROLE_ADMIN);
            Role userRole = new Role(Roles.ROLE_USER);

            UserRole role1 = new UserRole(user, adminRole);
            UserRole role2 = new UserRole(user, userRole);

            adminRole = roleRepository.save(adminRole);
            userRole = roleRepository.save(userRole);

            user = userRepository.save(user);

            role1.setId(new UserRoleKey(user.getId(), adminRole.getId()));
            role2.setId(new UserRoleKey(user.getId(), userRole.getId()));
            userRoleRepository.save(role1);
            userRoleRepository.save(role2);
        }
    }
}
