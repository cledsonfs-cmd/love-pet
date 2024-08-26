package me.dio.domain.service;

import me.dio.domain.model.entity.Pet;

import java.util.List;

public interface PetService {
    Pet getPetById(Long id);
    List<Pet> getAllPet();
    Pet savePet(Pet pet);
    String deletePet(Long id);
}
