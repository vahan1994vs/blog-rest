package com.vahan.blok.rest.service;

import com.vahan.blok.rest.dto.req.CommentCreateRequest;
import com.vahan.blok.rest.model.Comment;
import com.vahan.blok.rest.repository.CommentRepository;
import com.vahan.blok.rest.security.AuthSecurityService;
import com.vahan.blok.rest.util.CommentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AuthSecurityService authSecurityService;
    private final CommentUtil commentUtil;

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        Comment comment = new Comment();
        commentCreateRequest.setCommentText(comment.getCommentText());
        commentCreateRequest.setAuthorComment(authSecurityService.getCurrentUser());
        Clock cl = Clock.systemUTC();
        LocalDateTime datetime = LocalDateTime.now(cl);
        commentCreateRequest.setCreatedDate(datetime);
        commentCreateRequest.setUpdatedDate(datetime);
        return commentRepository.save(commentUtil.dtoToComment(commentCreateRequest));

    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }


}
