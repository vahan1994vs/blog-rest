package com.vahan.blok.rest.dto.req;

import com.vahan.blok.rest.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateRequest {
    private String title;
    private String description;
    private String mainPhoto;
    private User user;
    private LocalDateTime postCreatedDate;
    private LocalDateTime postUpdatedDate;
}
