package com.toyproject.hello.dev.image.service;

import com.toyproject.hello.dev.image.entity.Image;
import com.toyproject.hello.dev.image.repository.ImageRepository;
import com.toyproject.hello.dev.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    ImageHandler imageHandler = new ImageHandler();

    @Autowired
    ImageRepository imageRepository;

    public boolean saveImage(MultipartFile image) throws IOException, NoSuchAlgorithmException {
        String imageUrl = imageHandler.save(image);
        Image imageEntity = new Image();
        imageEntity.setImageUrl(imageUrl);
        imageRepository.save(imageEntity);
        return true;
    }

}
