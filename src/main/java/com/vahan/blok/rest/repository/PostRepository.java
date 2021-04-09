package com.vahan.blok.rest.repository;

import com.vahan.blok.rest.model.Comment;
import com.vahan.blok.rest.model.Post;
import com.vahan.blok.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCreatedBy(User user);
    Optional<Post> findById(Long id);
    List<Post> findAll();
}
