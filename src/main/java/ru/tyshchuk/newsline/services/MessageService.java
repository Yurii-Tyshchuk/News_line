package ru.tyshchuk.newsline.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tyshchuk.newsline.domain.*;
import ru.tyshchuk.newsline.domain.embeddings.MessageTagKey;
import ru.tyshchuk.newsline.dto.message.RequestMessage;
import ru.tyshchuk.newsline.repository.MessageAndTagRepository;
import ru.tyshchuk.newsline.repository.MessageRepository;
import ru.tyshchuk.newsline.repository.TagRepository;
import ru.tyshchuk.newsline.repository.UserRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MessageAndTagRepository messageAndTagRepository;

    public Message getMessageById(Long id) {
        Optional<Message> message = this.messageRepository.findById(id);
        return message.orElseThrow(() -> new RuntimeException("Message not found"));
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public List<Message> getMessagesByTag(String tagName) {
        Tag tag = this.tagRepository.findByName(tagName).orElseThrow(() -> new RuntimeException("Tag not found"));
        return messageRepository.findByTag_Id_TagId(tag.getId());
    }

    public void createMessage(RequestMessage requestMessage) {
        Message message = RequestMessage.create(requestMessage);
        List<Tag> tag = requestMessage.getTag();

        List<Tag> tags = tagRepository.findByIdIn(tag.stream().map(Tag::getId).toList());
        User user = this.userRepository.findByUsername(requestMessage.getUsername());

        message.setUser(user);
        message = this.messageRepository.save(message);

        List<MessageAndTag> buffer = new ArrayList<>();
        for (Tag tag1 : tags)
            buffer.add(new MessageAndTag(new MessageTagKey(message.getId(), tag1.getId())));

        this.messageAndTagRepository.saveAll(buffer);
    }

    public List<Message> gerRecommendation(String username) {
        User user = this.userService.findByUsername(username);
        Set<Long> tags = new HashSet<>();
        for (Like like : user.getLikes()) {
            List<Long> tagIds = like.getMessage().getTag().stream()
                    .map(messageAndTag -> messageAndTag.getId().getTagId())
                    .toList();
            tags.addAll(tagIds);
        }

        Set<Message> messages = new HashSet<>();
        for (Long tagId : tags) {
            List<Message> foundMessages = messageRepository.findByTag_Id_TagId(tagId);
            messages.addAll(foundMessages);
        }
        return messages.stream().toList();
    }
}
