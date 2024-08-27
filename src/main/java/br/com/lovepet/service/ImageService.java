package br.com.lovepet.service;

import br.com.lovepet.model.dto.CreateImageDto;
import br.com.lovepet.model.dto.ImageDto;

import java.util.List;

public interface ImageService {
    ImageDto getImageById(Long id);
    List<ImageDto> getAllImage();
    ImageDto saveImage(CreateImageDto image);
    String deleteImage(Long id);
}
