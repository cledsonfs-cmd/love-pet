package br.com.lovepet.controller;

import br.com.lovepet.model.dto.*;
import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(userService.atualizarUser(dto), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getEmail(@PathVariable("email") String valor){
        return new ResponseEntity<>(userService.obterPorEmail(valor), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getId(@PathVariable("id") Integer valor){
        return new ResponseEntity<>(userService.findById(valor), HttpStatus.OK);
    }

    @PutMapping("/ativar")
    public ResponseEntity<String> ativar(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(userService.ativar(dto), HttpStatus.OK);
    }

    @PutMapping("/suspender")
    public ResponseEntity<String> suspender(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(userService.suspender(dto), HttpStatus.OK);
    }

    @PutMapping("/inativar")
    public ResponseEntity<String> inativar(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(userService.inativar(dto), HttpStatus.OK);
    }

    @PutMapping("/excluir")
    public ResponseEntity<String> excluir(@RequestBody UsuarioDTO dto){
        return new ResponseEntity<>(userService.excluir(dto), HttpStatus.OK);
    }

}
