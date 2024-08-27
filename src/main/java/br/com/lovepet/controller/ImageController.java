package br.com.lovepet.controller;

import br.com.lovepet.model.dto.CreateImageDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import br.com.lovepet.model.dto.ImageDto;
import br.com.lovepet.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@Tag(name = "Images")
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
    public ResponseEntity<ImageDto> saveAdopter(@RequestBody CreateImageDto dto){
        return new ResponseEntity<>(service.saveImage(dto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ImageDto> updateAdopter(@RequestBody CreateImageDto dto){
        return new ResponseEntity<>(service.saveImage(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteImage(id), HttpStatus.OK);
    }
}
