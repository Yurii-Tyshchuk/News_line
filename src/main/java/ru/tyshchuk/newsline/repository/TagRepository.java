package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyshchuk.newsline.domain.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    List<Tag> findByIdIn(List<Long> ids);

    List<Tag> findAllByOrderByName();
}