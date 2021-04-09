package com.vahan.blok.rest.model;

import com.vahan.blok.rest.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "profilePicture")
    private String profilePicture;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "role")
    private Role role;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
}
