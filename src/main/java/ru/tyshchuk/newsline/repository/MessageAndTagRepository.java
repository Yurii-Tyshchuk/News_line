package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyshchuk.newsline.domain.MessageAndTag;
import ru.tyshchuk.newsline.domain.embeddings.MessageTagKey;

public interface MessageAndTagRepository extends JpaRepository<MessageAndTag, MessageTagKey> {
}