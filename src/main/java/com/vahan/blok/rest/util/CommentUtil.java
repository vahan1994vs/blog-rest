package com.vahan.blok.rest.util;

import com.vahan.blok.rest.dto.req.CommentCreateRequest;
import com.vahan.blok.rest.dto.req.PostCreateRequest;
import com.vahan.blok.rest.dto.resp.CommentCreateResponse;
import com.vahan.blok.rest.dto.resp.PostCreateResponse;
import com.vahan.blok.rest.model.Comment;
import com.vahan.blok.rest.model.Post;
import com.vahan.blok.rest.security.UserAuthAuthSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class CommentUtil {


    public Comment dtoToComment(CommentCreateRequest commentCreateRequest) {
        return Comment.builder()
                .commentText(commentCreateRequest.getCommentText())
                .createdDate(commentCreateRequest.getCreatedDate())
                .updatedDate(commentCreateRequest.getUpdatedDate())
                .build();
    }

    public CommentCreateResponse commentToDto(Comment comment) {
        return CommentCreateResponse.builder()
                .textComment(comment.getCommentText())
                .build();
    }
}
