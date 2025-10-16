package com.example.TDD_Template.domain.test.service;

import com.example.TDD_Template.domain.test.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // crud method impl

}
