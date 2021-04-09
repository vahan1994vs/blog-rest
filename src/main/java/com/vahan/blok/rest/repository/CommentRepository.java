package com.vahan.blok.rest.repository;

import com.vahan.blok.rest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
