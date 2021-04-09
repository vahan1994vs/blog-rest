package com.vahan.blok.rest.dto.resp;

import com.vahan.blok.rest.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterResponse {
    private String name;
    private String surname;
    private String email;
    private String profilePicture;
    private Role role;
}
