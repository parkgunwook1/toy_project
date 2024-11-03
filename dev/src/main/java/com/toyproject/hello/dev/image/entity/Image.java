package com.toyproject.hello.dev.image.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @NotNull
    @Column(name = "image_id")
    public int imageId;

    @Column(name = "image_url", length = 400)
    public String imageUrl;

    @Column(name = "post_id")
    public int postId;
}
