package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyshchuk.newsline.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String s);
}
