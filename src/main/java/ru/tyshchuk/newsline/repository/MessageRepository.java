package ru.tyshchuk.newsline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyshchuk.newsline.domain.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m where m.text like concat('%', ?1, '%') or m.header like concat('%', ?1, '%')")
    List<Message> findByTextContains(String text);
    @Query("select m from Message m inner join m.tag tag where ?1 like '%' || m.text || '%' and tag.tag.name in ?2")
    List<Message> findByTextContainsAndTag_Tag_Name(String text, List<String> tags);

    @Query("select m from Message m inner join m.tag tag where tag.id.tagId = ?1")
    List<Message> findByTag_Id_TagId(Long tagId);
}