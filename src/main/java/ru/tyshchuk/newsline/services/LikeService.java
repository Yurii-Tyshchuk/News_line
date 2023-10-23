package ru.tyshchuk.newsline.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tyshchuk.newsline.domain.Like;
import ru.tyshchuk.newsline.domain.Message;
import ru.tyshchuk.newsline.domain.User;
import ru.tyshchuk.newsline.repository.LikeRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public void dislike(User user, Message message) {
        likeRepository.deleteByUserAndMessage(user, message);
    }

    public void like(User user, Message message) {
        Like like = new Like(user, message);
        this.likeRepository.save(like);
    }

    public List<Like> getAllByName(User userName) {
        return likeRepository.findByUser(userName);
    }
}
