package br.com.lovepet.controller;

import br.com.lovepet.model.dto.*;
import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginRequestDTO);
        Usuario usuario = userService.obterPorEmail(loginRequestDTO.username());
        return new LoginResponseDTO(usuario.getId(), usuario.getEmail(), usuario.getNome(), token,usuario.getProvedor(), usuario.getImageUrl(),usuario.getRole());
    }

    @PostMapping("/refresh")
    public LoginResponseDTO refreshToken(@RequestBody LoginRequestDTO loginRequestDTO) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginRequestDTO);
        Usuario usuario = userService.obterPorEmail(loginRequestDTO.username());
        return new LoginResponseDTO(usuario.getId(), usuario.getEmail(), usuario.getNome(), token,usuario.getProvedor(), usuario.getImageUrl(),usuario.getRole());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return new ResponseEntity<>("Logout efetuado com sucesso!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public LoginResponseDTO createUser(@RequestBody CreateUserDto createUserDto) {
        Usuario usuario = userService.createUser(createUserDto);
        RecoveryJwtTokenDto token = userService.authenticateUser(new LoginRequestDTO(createUserDto.email(), createUserDto.password()));
        return new LoginResponseDTO(usuario.getId(), usuario.getEmail(), usuario.getNome(), token, usuario.getProvedor(), usuario.getImageUrl(),usuario.getRole());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsuarioDTO>> all(){
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        for (Usuario usuario:userService.findAll()){
            usuarioDTOS.add(new UsuarioDTO(
                    usuario.getId(),
                    usuario.getEmail(),
                    usuario.getNome(),
                    usuario.getProvedor(),
                    usuario.getImageUrl(),
                    usuario.getIdStatus(),
                    usuario.getRole()));
        }
        return new ResponseEntity<>(usuarioDTOS, HttpStatus.OK);
    }
}
