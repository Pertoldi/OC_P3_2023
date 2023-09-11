package com.backend.chatop4.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class ImageService {
  public ResponseEntity<Object> getPictureByName(String name) throws IOException {
    String imagePath = "C:\\Users\\Pertoldi\\Desktop\\OC2023\\projet_3.1\\OC_P3_2023\\pictures\\" // TODO passer en
                                                                                                  // properties +
                                                                                                  // expliquer dans la
                                                                                                  // doc
        + name;
    File imageFile = new File(imagePath);
    if (!imageFile.exists()) {
      return ResponseEntity.notFound().build();
    }
    InputStream inputStream = new FileInputStream(imageFile);
    byte[] imageBytes = FileCopyUtils.copyToByteArray(inputStream);
    String contentType = MediaType.IMAGE_JPEG_VALUE;
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .body(imageBytes);
  }
}
