package ru.tyshchuk.newsline.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tyshchuk.newsline.domain.Message;
import ru.tyshchuk.newsline.dto.message.RequestMessage;
import ru.tyshchuk.newsline.dto.message.SearchMessage;
import ru.tyshchuk.newsline.services.MessageService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/getById")
    public Message getMessageById(Long id) {
        try {
            return this.messageService.getMessageById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping("/getAll")
    public List<Message> getAllMessages() {
        try {
            return this.messageService.getAllMessages();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping("/create")
    public ResponseEntity<?> createMessage(@RequestBody RequestMessage requestMessage) {
        try {
            this.messageService.createMessage(requestMessage);
            return ResponseEntity.ok("Message created successfully");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping("/getRecommended")
    public List<Message> getRecommendation(@RequestParam("username") String username) {
        try {
            return this.messageService.gerRecommendation(username);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/getByTextAndTags", method = RequestMethod.POST)
    public List<Message> getByTextAndTags(@RequestBody SearchMessage message) {
        try {
            return this.messageService.getByTextAndTags(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return List.of();
        }
    }
}
