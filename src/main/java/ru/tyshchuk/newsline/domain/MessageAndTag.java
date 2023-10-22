package ru.tyshchuk.newsline.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.tyshchuk.newsline.domain.embeddings.MessageTagKey;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "message_tag")
@AllArgsConstructor
@NoArgsConstructor
public class MessageAndTag {
    @EmbeddedId
    private MessageTagKey id;
    @ManyToOne
    @MapsId("messageId")
    @JoinColumn(name = "message_id")
    @JsonBackReference
    private Message message;
    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    @JsonBackReference
    private Tag tag;

    public MessageAndTag(MessageTagKey id) {
        this.id = id;
    }
}
