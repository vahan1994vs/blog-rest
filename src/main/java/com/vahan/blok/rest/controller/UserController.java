package com.vahan.blok.rest.controller;

import com.vahan.blok.rest.dto.req.UserAuthRequest;
import com.vahan.blok.rest.model.User;
import com.vahan.blok.rest.dto.req.UserRegisterRequest;
import com.vahan.blok.rest.dto.resp.UserAuthResponse;
import com.vahan.blok.rest.exception.ModelAlreadyExistException;
import com.vahan.blok.rest.exception.ModelNotFoundException;
import com.vahan.blok.rest.security.UserAuthAuthSecurityService;
import com.vahan.blok.rest.service.UserService;
import com.vahan.blok.rest.util.JwtTokenUtil;
import com.vahan.blok.rest.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;




    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) throws IOException, ModelAlreadyExistException {
        if (userService.isUserExist(userRegisterRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userService.register(userRegisterRequest);
        return ResponseEntity.ok(UserUtil.userToDto(user));
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> auth(@RequestBody UserAuthRequest userAuthRequest, HttpServletRequest request) {
        User user = null;
        try {
            user = userService.findByEmail(userAuthRequest.getEmail());

        } catch (ModelNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        if (passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            request.getSession().setAttribute("token", token);
            return ResponseEntity.ok(UserAuthResponse.builder()
                    .userRegisterResponse(UserUtil.userToDto(user))
                    .token(token)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
