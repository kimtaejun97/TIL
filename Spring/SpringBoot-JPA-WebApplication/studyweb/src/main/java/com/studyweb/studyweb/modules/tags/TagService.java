package com.studyweb.studyweb.modules.tags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public List<String> getAllTagTitle() {
        return tagRepository.findAll().stream()
                .map(Tag::getTitle)
                .collect(Collectors.toList());
    }
}

