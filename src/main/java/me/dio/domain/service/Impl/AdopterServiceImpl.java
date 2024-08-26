package me.dio.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.entity.Adopter;
import me.dio.domain.repository.AdopterRepository;
import me.dio.domain.service.AdopterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdopterServiceImpl implements AdopterService {

    private final AdopterRepository adopterRepository;

    @Override
    public Adopter getAdopterById(Long id) {
        return adopterRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Adopter> getAllAdopters() {
        return adopterRepository.findAll();
    }

    @Override
    public Adopter saveAdopter(Adopter adopter) {
        return adopterRepository.save(adopter);
    }

    @Override
    public String deleteAdopter(Long id) {
        if(adopterRepository.existsById(id)) {
            adopterRepository.deleteById(id);
        }else{
            throw new NoSuchElementException();
        }
        return "Adopter excluído com sucesso!";
    }
}
