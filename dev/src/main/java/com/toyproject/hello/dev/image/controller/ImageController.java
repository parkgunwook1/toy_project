package com.toyproject.hello.dev.image.controller;

import com.toyproject.hello.dev.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/upload")
    public String getUploadPage(){
        return "upload";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + "C:\\project\\toy_project\\dev\\src\\main\\resources\\static\\imgs\\" + filename);
    }

    @PostMapping("/save")
    public String postSaveFile(@RequestParam MultipartFile image) throws IOException, NoSuchAlgorithmException {
        if(!image.isEmpty()){
            imageService.saveImage(image);
        }
        return "redirect:/view";
    }
}
