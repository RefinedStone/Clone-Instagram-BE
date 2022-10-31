package com.example.cloneinstargram.feed.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class S3image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    private Feed feed;

    public S3image(String image, Feed feed){
        this.image = image;
        this.feed = feed;
    }
}
