package me.dio.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.entity.Pet;
import me.dio.domain.repository.PetRepository;
import me.dio.domain.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Pet> getAllPet() {
        return petRepository.findAll();
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
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
