package com.vahan.blok.rest.service;

import com.vahan.blok.rest.dto.req.PostCreateRequest;
import com.vahan.blok.rest.model.Comment;
import com.vahan.blok.rest.model.Post;
import com.vahan.blok.rest.model.User;
import com.vahan.blok.rest.repository.PostRepository;
import com.vahan.blok.rest.security.AuthSecurityService;
import com.vahan.blok.rest.security.CurrentUser;
import com.vahan.blok.rest.security.UserAuthAuthSecurityService;
import com.vahan.blok.rest.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${image.upload.dir}")
    private String uploadDir;
    private final UserAuthAuthSecurityService authSecurityService;
    private final PostRepository postRepository;
    private final PostUtil postUtil;

    public List<Post> getAllPostsByUser(User user) {
        return postRepository.findAllByCreatedBy(user);
    }

    public Optional<Post> getAllPostsByTitle(Long id) {
        return postRepository.findById(id);
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Post addPost(PostCreateRequest postCreateRequest, MultipartFile file) throws IOException {
        User user = authSecurityService.getCurrentUser();
        Post post = new Post();
        if (file == null) {
            post.setMainPhoto("without photo");
        }
//        Clock cl = Clock.systemUTC();
//        LocalDateTime datetime = LocalDateTime.now(cl);
//        postCreateRequest.setTitle(post.getTitle());
//        postCreateRequest.setDescription(post.getDescription());
//        postCreateRequest.setUser(post.getCreatedBy());
//        postCreateRequest.setPostCreatedDate(datetime);
//        postCreateRequest.setPostUpdatedDate(datetime);
        Clock cl = Clock.systemUTC();
        LocalDateTime datetime = LocalDateTime.now(cl);
        post.setTitle(postCreateRequest.getTitle());
        post.setDescription(postCreateRequest.getDescription());
        post.setCreatedBy(user);
        post.setPostCreatedDate(datetime);
        post.setPostUpdatedDate(datetime);
        return postRepository.save(post);
    }

    public Post updatePost(PostCreateRequest postCreateRequest, MultipartFile file) throws IOException {
        Post post = new Post();
        if (!file.isEmpty()) {
            String name = System.currentTimeMillis() + "_" + file.getName();
            File image = new File(uploadDir, name);
            file.transferTo(image);
            post.setMainPhoto(name);
        } else {
            Optional<Post> oldImage = postRepository.findById(post.getId());
            postCreateRequest.setMainPhoto(oldImage.get().getMainPhoto());
        }
        Clock clock = Clock.systemUTC();
        LocalDateTime datetime = LocalDateTime.now(clock);
        postCreateRequest.setTitle(post.getTitle());
        postCreateRequest.setDescription(post.getDescription());
//        postCreateRequest.setUser(authSecurityService.getCurrentUser());
        postCreateRequest.setPostCreatedDate(datetime);
        postCreateRequest.setPostUpdatedDate(datetime);
        return postRepository.save(postUtil.dtoToPost(postCreateRequest));
    }




}



