package ru.tyshchuk.newsline.services;

import org.springframework.stereotype.Service;
import ru.tyshchuk.newsline.repository.TagRepository;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
}
