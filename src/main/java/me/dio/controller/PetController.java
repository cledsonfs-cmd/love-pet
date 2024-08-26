package me.dio.controller;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.AdopterDto;
import me.dio.domain.model.dto.PetDto;
import me.dio.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService service;

    @GetMapping()
    public ResponseEntity<List<PetDto>> getAll(){
        return new ResponseEntity<>(service.getAllPet(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getId(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getPetById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PetDto> saveAdopter(@RequestBody PetDto dto){
        return new ResponseEntity<>(service.savePet(dto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PetDto> updateAdopter(@RequestBody PetDto dto){
        return new ResponseEntity<>(service.savePet(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deletePet(id), HttpStatus.OK);
    }
}
