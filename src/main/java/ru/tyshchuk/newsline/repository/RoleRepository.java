package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyshchuk.newsline.domain.Role;
import ru.tyshchuk.newsline.domain.Roles;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(Roles roleUser);
}