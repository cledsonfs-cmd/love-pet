package me.dio.controller;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.PetDto;
import me.dio.domain.model.dto.UserDto;
import me.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(service.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getId(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> saveAdopter(@RequestBody UserDto dto){
        return new ResponseEntity<>(service.saveUser(dto), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateAdopter(@RequestBody UserDto dto){
        return new ResponseEntity<>(service.saveUser(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }
}
