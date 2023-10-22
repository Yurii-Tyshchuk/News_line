package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.tyshchuk.newsline.domain.Like;
import ru.tyshchuk.newsline.domain.Message;
import ru.tyshchuk.newsline.domain.User;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Transactional
    @Modifying
    @Query("delete from Like l where l.user = ?1 and l.message = ?2")
    int deleteByUserAndMessage(User user, Message message);
}