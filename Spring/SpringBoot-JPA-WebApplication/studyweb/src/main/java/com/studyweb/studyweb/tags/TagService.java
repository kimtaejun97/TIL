package com.studyweb.studyweb.tags;

import com.studyweb.studyweb.domain.Tag;
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

