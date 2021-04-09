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
public class CommentCreateRequest {
    private String commentText;
    private User authorComment;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
