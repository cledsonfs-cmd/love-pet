package me.dio.service;

import me.dio.domain.model.dto.PetDto;
import me.dio.domain.model.entity.Pet;

import java.util.List;

public interface PetService {
    PetDto getPetById(Long id);
    List<PetDto> getAllPet();
    PetDto savePet(PetDto dto);
    String deletePet(Long id);
}
