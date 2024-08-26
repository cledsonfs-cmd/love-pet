package me.dio.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.PetDto;
import me.dio.domain.model.entity.Pet;
import me.dio.domain.repository.PetRepository;
import me.dio.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public PetDto getPetById(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(pet, PetDto.class);
    }

    @Override
    public List<PetDto> getAllPet() {
        List<Pet> pets = petRepository.findAll();
        List<PetDto> petDtos = new ArrayList<>();
        modelMapper.map(pets, petDtos);
        return petDtos;
    }

    @Override
    public PetDto savePet(PetDto dto) {
        Pet pet = modelMapper.map(dto, Pet.class);
        petRepository.save(pet);
        return modelMapper.map(pet, PetDto.class);
    }

    @Override
    public String deletePet(Long id) {
        if(petRepository.existsById(id)) {
            petRepository.deleteById(id);
        }else{
            throw new NoSuchElementException();
        }
        return "Pet excluído com sucesso!";
    }
}
