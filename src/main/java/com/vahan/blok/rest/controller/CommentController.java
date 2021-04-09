package com.vahan.blok.rest.controller;

import com.vahan.blok.rest.dto.req.CommentCreateRequest;
import com.vahan.blok.rest.model.Comment;
import com.vahan.blok.rest.service.CommentService;
import com.vahan.blok.rest.util.CommentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentUtil commentUtil;

    @RequestMapping(value = "add-comment", method = RequestMethod.POST)
    public ResponseEntity<?> newComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        Comment comment = commentService.createComment(commentCreateRequest);
        return ResponseEntity.ok(commentUtil.commentToDto(comment));
    }

    @RequestMapping(value = "edit-comment", method = RequestMethod.POST)
    public ResponseEntity<?> editComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        Comment comment = commentService.createComment(commentCreateRequest);
        return ResponseEntity.ok(commentUtil.commentToDto(comment));
    }

    @RequestMapping(value = "delete{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
