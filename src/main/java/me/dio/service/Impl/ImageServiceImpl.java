package me.dio.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.ImageDto;
import me.dio.domain.model.entity.Image;
import me.dio.domain.repository.ImageRepository;
import me.dio.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ImageDto getImageById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(image, ImageDto.class);
    }

    @Override
    public List<ImageDto> getAllImage() {
        List<Image> images = imageRepository.findAll();
        List<ImageDto> dtos = new ArrayList<>();
        modelMapper.map(images, dtos);
        return dtos;
    }

    @Override
    public ImageDto saveImage(ImageDto dto) {
        Image image = modelMapper.map(dto, Image.class);
        imageRepository.save(image);
        return modelMapper.map(image, ImageDto.class);
    }

    @Override
    public String deleteImage(Long id) {
        if(imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        }else{
            throw new NoSuchElementException();
        }
        return "Image excluído com sucesso!";
    }
}
