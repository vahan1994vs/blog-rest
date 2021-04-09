package com.vahan.blok.rest.service;

import com.vahan.blok.rest.dto.req.UserRegisterRequest;
import com.vahan.blok.rest.exception.ModelNotFoundException;
import com.vahan.blok.rest.model.User;
import com.vahan.blok.rest.security.AuthSecurityService;
import com.vahan.blok.rest.util.RandomStringGeneratorUtil;
import com.vahan.blok.rest.util.UserUtil;
import com.vahan.blok.rest.exception.ModelAlreadyExistException;
import com.vahan.blok.rest.model.enums.Role;
import com.vahan.blok.rest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${image.upload.dir}")
    private String uploadDir;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthSecurityService authSecurityService;

    public User register(UserRegisterRequest userRegisterRequest) throws IOException, ModelAlreadyExistException {
        if (isUserExist(userRegisterRequest.getEmail())) {
            throw new ModelAlreadyExistException(String.format("User with '%s' email was already exist.", userRegisterRequest.getEmail()));
        }
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User user = UserUtil.dtoToUser(userRegisterRequest);
        return userRepository.save(user);
    }

    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User findByEmail(String email) throws ModelNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(ModelNotFoundException::new);
    }


    public List<User> findByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void getProfilePicture(HttpServletResponse response, String picUrl) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    public String uploadProfilePicture(MultipartFile file) throws IOException {
        String name = RandomStringGeneratorUtil.generateRandomStringByUUID() + "_" + file.getOriginalFilename();
        File upImage = new File(uploadDir, name);
        file.transferTo(upImage);
        User user = authSecurityService.getCurrentUser();
        user.setProfilePicture(name);
        userRepository.save(user);
        return name;
    }

}
