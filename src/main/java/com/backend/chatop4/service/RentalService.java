package com.backend.chatop4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.chatop4.model.Rental;
import com.backend.chatop4.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  public Rental create(Rental rental) {
    // TODO enregistrer l'image et remplacer l'url avant de l'enregistrer
    return (Rental) rentalRepository.save(rental);
  }

  public List<Rental> getAll() {
    return rentalRepository.findAll();
  }

  Rental update(Long id, Rental rental) {
    // TODO IDEM gestion de l'image si celle-ci à été modifié
    Rental r = rentalRepository.getReferenceById(id);
    r.setPrice(rental.getPrice());
    r.setPicture(rental.getPicture());
    r.setOwner_id(rental.getOwner_id());
    r.setCreated_at(rental.getCreated_at());
    r.setUpdated_at(rental.getUpdated_at());
    return r;
  }

  public Rental getOne(Long id) {
    return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found !"));
  }
}
