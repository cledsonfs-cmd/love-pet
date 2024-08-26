package me.dio.domain.service;

import me.dio.domain.model.entity.Adopter;

import java.util.List;

public interface AdopterService {
    Adopter getAdopterById(Long id);
    List<Adopter> getAllAdopters();
    Adopter saveAdopter(Adopter adopter);
    String deleteAdopter(Long id);
}
