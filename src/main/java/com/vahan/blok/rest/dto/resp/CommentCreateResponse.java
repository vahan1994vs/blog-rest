package com.vahan.blok.rest.dto.resp;

import com.vahan.blok.rest.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateResponse {
    private String textComment;
    private User authorComment;
}
