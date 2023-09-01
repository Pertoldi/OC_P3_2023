package com.backend.chatop4.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.chatop4.model.Rental;
import com.backend.chatop4.repository.RentalRepository;

import io.jsonwebtoken.io.IOException;
import lombok.Data;

@Data
@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  public List<Rental> getAll() {
    return rentalRepository.findAll();
  }

  Rental update(Integer id, Rental rental) {
    // TODO IDEM gestion de l'image si celle-ci à été modifié
    Rental r = rentalRepository.getReferenceById(id);
    r.setPrice(rental.getPrice());
    r.setPicture(rental.getPicture());
    r.setOwner_id(rental.getOwner_id());
    r.setCreated_at(rental.getCreated_at());
    r.setUpdated_at(rental.getUpdated_at());
    return r;
  }

  public Rental getOne(Integer id) {
    return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found !"));
  }

  public Rental create(Rental rental, Integer userId) {
    rental.setOwner_id(userId);
    Rental rentalSaved = rentalRepository.save(rental);
    return rentalSaved;
  }

  public String uploadPicture(MultipartFile imageFile) throws IllegalStateException, java.io.IOException {
    try {
      if (imageFile.getSize() > 10485760) { // 10 MB to bytes
        throw new IOException("File size exceeds the limit.");
      }
      String uploadPath = "C:\\Users\\Pertoldi\\Desktop\\OC2023\\projet_3.1\\";
      String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
      String filePath = uploadPath + fileName;
      System.out.println("filePath: " + filePath);
      File pictureDir = ResourceUtils.getFile(uploadPath);
      if (!pictureDir.exists()) {
        pictureDir.mkdirs();
      }
      imageFile.transferTo(new File(filePath));
      String addressAndPort = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
      String imageUrl = addressAndPort + "/picture/" + fileName;
      return imageUrl;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }
}
