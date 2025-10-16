package com.example.TDD_Template.domain.test.repository;

import com.example.TDD_Template.domain.test.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
