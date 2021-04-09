package com.vahan.blok.rest.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateResponse {
    private String title;
    private String description;
    private UserRegisterResponse userRegisterResponse;
    private CommentCreateResponse commentCreateResponse;


}
