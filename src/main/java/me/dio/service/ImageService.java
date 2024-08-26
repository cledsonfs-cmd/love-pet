package me.dio.service;

import me.dio.domain.model.dto.ImageDto;
import me.dio.domain.model.entity.Image;

import java.util.List;

public interface ImageService {
    ImageDto getImageById(Long id);
    List<ImageDto> getAllImage();
    ImageDto saveImage(ImageDto image);
    String deleteImage(Long id);
}
