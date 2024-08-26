package me.dio.controller;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.AdopterDto;
import me.dio.domain.model.dto.ImageDto;
import me.dio.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService service;

    @GetMapping()
    public ResponseEntity<List<ImageDto>> getAll(){
        return new ResponseEntity<>(service.getAllImage(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getId(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getImageById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ImageDto> saveAdopter(@RequestBody ImageDto dto){
        return new ResponseEntity<>(service.saveImage(dto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ImageDto> updateAdopter(@RequestBody ImageDto dto){
        return new ResponseEntity<>(service.saveImage(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteImage(id), HttpStatus.OK);
    }
}
