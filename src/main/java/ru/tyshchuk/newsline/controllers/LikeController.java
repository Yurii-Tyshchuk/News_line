package ru.tyshchuk.newsline.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tyshchuk.newsline.domain.Message;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.services.LikeService;
import ru.tyshchuk.newsline.services.MessageService;
import ru.tyshchuk.newsline.services.UserService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/like")
public class LikeController {
    private final UserService userService;
    private final LikeService likeService;
    private final MessageService messageService;

    @GetMapping("/change")
    public ResponseEntity change(
            @RequestParam("change") boolean change,
            @RequestParam("username") String username,
            @RequestParam("message_id") Long messageId) {
        try {
            User user = this.userService.findByUsername(username);
            Message message = this.messageService.getMessageById(messageId);
            if (change) {
                this.likeService.like(user, message);
            } else {
                this.likeService.dislike(user, message);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }
}
