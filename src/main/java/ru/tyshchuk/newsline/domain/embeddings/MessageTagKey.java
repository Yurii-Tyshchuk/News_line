package ru.tyshchuk.newsline.domain.embeddings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MessageTagKey implements Serializable {
    @Column(name = "message_id", nullable = false)
    private Long messageId;
    @Column(name = "tag_id", nullable = false)
    private Long tagId;
}
