package ru.tyshchuk.newsline.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tyshchuk.newsline.domain.Role;
import ru.tyshchuk.newsline.domain.Roles;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.domain.UserRole;
import ru.tyshchuk.newsline.domain.embeddings.UserRoleKey;
import ru.tyshchuk.newsline.dto.user.RequestUser;
import ru.tyshchuk.newsline.repository.RoleRepository;
import ru.tyshchuk.newsline.repository.UserRepository;
import ru.tyshchuk.newsline.repository.UserRoleRepository;

import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(RequestUser user) {
        User user1 = RequestUser.create(user);
        user1.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roleUser = roleRepository.findByRole(Roles.ROLE_USER);
        UserRole userRole = new UserRole(user1, roleUser);

        user1 = userRepository.save(user1);

        userRole.setId(new UserRoleKey(user1.getId(), roleUser.getId()));
        userRoleRepository.save(userRole);
    }

    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            throw new RuntimeException("User not found");
        }
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            return null;
        }
        return result;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
