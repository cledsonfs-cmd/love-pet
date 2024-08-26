package me.dio.domain.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.entity.Image;
import me.dio.domain.repository.ImageRepository;
import me.dio.domain.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
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
