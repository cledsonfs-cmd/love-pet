package me.dio.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.AdopterDto;
import me.dio.domain.model.entity.Adopter;
import me.dio.domain.repository.AdopterRepository;
import me.dio.service.AdopterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdopterServiceImpl implements AdopterService {

    @Autowired
    private AdopterRepository adopterRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public AdopterDto getAdopterById(Long id) {
        Adopter adopter = adopterRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(adopter, AdopterDto.class);
    }

    @Override
    public List<AdopterDto> getAllAdopters() {
        List<Adopter> adopters = adopterRepository.findAll();
        List<AdopterDto> dtos = new ArrayList<>();
        modelMapper.map(adopters, dtos);
        return dtos;
    }

    @Override
    public AdopterDto saveAdopter(AdopterDto dto) {
        Adopter adopter = modelMapper.map(dto, Adopter.class);
        adopterRepository.save(adopter);
        return modelMapper.map(adopter, AdopterDto.class);
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
