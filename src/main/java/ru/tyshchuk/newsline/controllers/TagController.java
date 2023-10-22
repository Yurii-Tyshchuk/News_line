package ru.tyshchuk.newsline.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tyshchuk.newsline.domain.Tag;
import ru.tyshchuk.newsline.repository.TagRepository;
import ru.tyshchuk.newsline.services.TagService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagRepository tagRepository;
    private final TagService tagService;

    public TagController(TagRepository tagRepository, TagService tagService) {
        this.tagRepository = tagRepository;
        this.tagService = tagService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity create(@RequestParam("name") String name) {
        try {
            if (name == null || name.isEmpty()) {
                return ResponseEntity.badRequest().body("Empty name");
            }
            Tag tag = new Tag(name);
            tagRepository.save(tag);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getByName(@RequestParam("name") String name) {
        try {
            if (name == null || name.isEmpty()) {
                return ResponseEntity.badRequest().body("Empty name");
            }
            Optional<Tag> tag = this.tagRepository.findByName(name);
            Tag tag1 = tag.orElseThrow();
            return ResponseEntity.ok(tag1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        try {
            List<Tag> tags = this.tagRepository.findAllByOrderByName();
            return ResponseEntity.ok(tags);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
