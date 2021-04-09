package com.vahan.blok.rest.security;

import com.vahan.blok.rest.model.User;

@FunctionalInterface
public interface AuthSecurityService {
    User getCurrentUser();
}
