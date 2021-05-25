package com.vahan.blok.rest.controller;


import com.vahan.blok.rest.dto.req.PostCreateRequest;
import com.vahan.blok.rest.dto.resp.PostCreateResponse;
import com.vahan.blok.rest.model.Post;
import com.vahan.blok.rest.security.AuthSecurityService;
import com.vahan.blok.rest.security.CurrentUser;
import com.vahan.blok.rest.service.PostService;
import com.vahan.blok.rest.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("post/")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostUtil postUtil;
    private final AuthSecurityService authSecurityService;

    @RequestMapping(value = "new-post", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody PostCreateRequest postCreateRequest, MultipartFile file) throws IOException {
        Post post1 = postService.addPost(postCreateRequest, file);
        return ResponseEntity.ok(postUtil.postToDto(post1));
    }

    @RequestMapping(value = "get-currentUser-posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> searchCurrentUserPosts() {
        List<Post> allPostsByUser = postService.getAllPostsByUser(authSecurityService.getCurrentUser());
        return ResponseEntity.ok(allPostsByUser);
    }

    @RequestMapping(value = "get-all-posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> allPost = postService.findAllPost();
        return ResponseEntity.ok(allPost);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
