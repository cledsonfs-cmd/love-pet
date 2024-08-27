package br.com.lovepet.service;

import br.com.lovepet.model.dto.CreatePetDto;
import br.com.lovepet.model.dto.PetDto;

import java.util.List;

public interface PetService {
    PetDto getPetById(Long id);
    List<PetDto> getAllPet();
    PetDto savePet(CreatePetDto dto);
    PetDto updatePet(PetDto dto);
    String deletePet(Long id);
}
