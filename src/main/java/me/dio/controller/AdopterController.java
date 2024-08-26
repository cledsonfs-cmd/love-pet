package me.dio.controller;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.AdopterDto;
import me.dio.domain.model.dto.UserDto;
import me.dio.service.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adopters")
public class AdopterController {

    @Autowired
    private AdopterService service;

    @GetMapping()
    public ResponseEntity<List<AdopterDto>> getAll(){
        return new ResponseEntity<>(service.getAllAdopters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdopterDto> getId(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getAdopterById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AdopterDto> saveAdopter(@RequestBody AdopterDto dto){
        return new ResponseEntity<>(service.saveAdopter(dto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<AdopterDto> updateAdopter(@RequestBody AdopterDto dto){
        return new ResponseEntity<>(service.saveAdopter(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteAdopter(id), HttpStatus.OK);
    }

}
