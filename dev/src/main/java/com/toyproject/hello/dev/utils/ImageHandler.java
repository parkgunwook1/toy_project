package com.toyproject.hello.dev.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class ImageHandler {
    // 전달받은 이미지를 저장한다.
    public String save(MultipartFile image) throws IOException , NoSuchAlgorithmException{
        String originalName = getOriginName(image);
        String uniqueFileName = generateUniqueFileName(originalName);
        String fullpathName = "C:\\project\\toy_project\\dev\\src\\main\\resources\\static\\imgs\\" + uniqueFileName;
        image.transferTo(new File(fullpathName));
        return fullpathName;
    }
    // 전달받은 이미지 파일의 이름을 반환한다.
    public String getOriginName(MultipartFile image) {
        return image.getOriginalFilename();
    }

    public String generateUniqueFileName(String originalFileName) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalFileName.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String shortOriginalName = originalFileName.length() > 4 ? originalFileName.substring(0,4) : originalFileName;

        return shortOriginalName + "_" + hexString.toString() + fileExtension;
    }
}

/*절대경로
*
* 스프링에서는 파일을 저장할 때 절대 경로의 사용을 권장한다.
*
* 저장하려는 파일과 같은 이름을 가진 파일이 기존에 존재할 경우, 해당 파일은 덮어 싀어진다.
* 이를 위해 새로 저장하는 파일의 이름은 각자의 방법대로 기존 파일과 구별되는 이름임을 보장해주어야 한다.
* */
