package com.vahan.blok.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //    @Size(min = 10, max = 15)
    private String title;
    private String description;
    private String mainPhoto;
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
    private LocalDateTime postCreatedDate;
    private LocalDateTime postUpdatedDate;

}
