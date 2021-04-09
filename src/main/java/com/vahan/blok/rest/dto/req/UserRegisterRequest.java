package com.vahan.blok.rest.dto.req;

import com.vahan.blok.rest.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
