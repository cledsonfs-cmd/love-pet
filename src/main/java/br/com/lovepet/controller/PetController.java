package br.com.lovepet.controller;

import br.com.lovepet.model.dto.CreatePetDto;
import br.com.lovepet.model.dto.PetDto;
import br.com.lovepet.service.PetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pets")
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
    public ResponseEntity<PetDto> saveAdopter(@RequestBody CreatePetDto dto){
        return new ResponseEntity<>(service.savePet(dto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PetDto> updateAdopter(@RequestBody PetDto dto){
        return new ResponseEntity<>(service.updatePet(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deletePet(id), HttpStatus.OK);
    }
}
