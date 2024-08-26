package me.dio.service;

import me.dio.domain.model.dto.AdopterDto;
import me.dio.domain.model.entity.Adopter;

import java.util.List;

public interface AdopterService {
    AdopterDto getAdopterById(Long id);
    List<AdopterDto> getAllAdopters();
    AdopterDto saveAdopter(AdopterDto adopter);
    String deleteAdopter(Long id);
}
