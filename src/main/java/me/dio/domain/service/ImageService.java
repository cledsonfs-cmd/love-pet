package me.dio.domain.service;

import me.dio.domain.model.entity.Image;

import java.util.List;

public interface ImageService {
    Image getImageById(Long id);
    List<Image> getAllImage();
    Image saveImage(Image image);
    String deleteImage(Long id);
}
