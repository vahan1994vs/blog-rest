package com.vahan.blok.rest.util;


import com.vahan.blok.rest.dto.req.PostCreateRequest;
import com.vahan.blok.rest.dto.resp.PostCreateResponse;
import com.vahan.blok.rest.model.Post;
import com.vahan.blok.rest.model.User;
import com.vahan.blok.rest.security.AuthSecurityService;
import com.vahan.blok.rest.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUtil {


    public  Post dtoToPost(PostCreateRequest post ) {
        return Post.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .postCreatedDate(post.getPostCreatedDate())
                .postUpdatedDate(post.getPostUpdatedDate())
                .build();
    }

    public PostCreateResponse postToDto(Post post) {
        return PostCreateResponse.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .build();
    }
}
