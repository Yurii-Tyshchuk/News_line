package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyshchuk.newsline.domain.UserRole;
import ru.tyshchuk.newsline.domain.UserRoleKey;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
}