package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyshchuk.newsline.domain.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m inner join m.tag tag where tag.id.tagId = ?1")
    List<Message> findByTag_Id_TagId(Long tagId);
}