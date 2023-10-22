package ru.tyshchuk.newsline.dto.message;

import lombok.Getter;
import lombok.Setter;
import ru.tyshchuk.newsline.domain.Message;
import ru.tyshchuk.newsline.domain.Tag;

import java.util.List;

@Getter
@Setter
public class RequestMessage {
    private String header;
    private String text;
    private List<Tag> tag;
    private String username;

    public static Message create(RequestMessage request) {
        return Message.builder()
                .header(request.header)
                .text(request.text)
                .build();
    }
}
