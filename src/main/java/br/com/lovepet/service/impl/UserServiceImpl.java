package br.com.lovepet.service.impl;

import br.com.lovepet.enums.RoleName;
import br.com.lovepet.enums.UsuarioEvents;
import br.com.lovepet.model.UserDetailsImpl;
import br.com.lovepet.model.dto.*;
import br.com.lovepet.model.entity.Role;
import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.model.entity.UsuarioHistorico;
import br.com.lovepet.repository.UsuarioRepository;
import br.com.lovepet.security.SecurityConfiguration;
import br.com.lovepet.security.service.JwtTokenService;
import br.com.lovepet.service.UserService;
import br.com.lovepet.statemachine.service.StateMachineEventService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    private final UsuarioRepository usuarioRepository;

    private final SecurityConfiguration securityConfiguration;

    private final StateMachineEventService stateMachineEventService;

    private final ModelMapper modelMapper = new ModelMapper();

    // Método responsável por autenticar um usuário e retornar um token JWT
    @Override
    public RecoveryJwtTokenDto authenticateUser(@NotNull LoginRequestDTO dto) {
        if (dto.username().isEmpty()) {
            throw new IllegalArgumentException("username cannot be empty!");
        }

        if (dto.password().isEmpty()) {
            throw new IllegalArgumentException("password cannot be empty!");
        }

        try {
            obterPorEmail(dto.username());
        } catch (RuntimeException e) {
            throw new NoSuchElementException("username not found!");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(authentication ==null){
            throw new NoSuchElementException("username not found!");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    @Override
    public Usuario createUser(@NotNull CreateUserDto dto) {

        if (dto.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome cannot be empty!");
        }

        if (dto.email().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty!");
        }

        if (dto.password().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty!");
        }

        if (dto.password().trim().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters!");
        }

        Optional<Usuario> usuarioExiste = usuarioRepository.findByEmail(dto.email());

        if (!usuarioExiste.isEmpty()) {
            throw new InputMismatchException("Já existe um usuario cadastro com o email: " + dto.email());
        }

        Usuario newUsuario = Usuario.builder()
                .email(dto.email())
                .nome(dto.nome())
                .idStatus(dto.idstatus())
                .password(securityConfiguration.passwordEncoder().encode(dto.password()))
                .role(
                        Role.builder()
                                .id(dto.role()== RoleName.ROLE_ADMINISTRATOR ? 1L : 2L)
                                .name(dto.role())
                                .build()
                )
                .build();

        usuarioRepository.save(newUsuario);
        return newUsuario;
    }

    @Override
    public String atualizarUser(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            throw new RuntimeException("Objeto não informado!");
        }

        Usuario objeto = usuarioRepository.findByEmail(usuarioDTO.email()).get();
        modelMapper.map(usuarioDTO, objeto);
        usuarioRepository.save(objeto);

        return "Usuario atualizado com sucesso!";
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum registro encontrado."));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obterPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Nenhum registro encontrado."));
    }

    @Override
    public String ativar(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .id(dto.id())
                .email(dto.email())
                .idStatus(dto.idstatus())
                .build();
        UsuarioEvents events = UsuarioEvents.ATIVAR;

        events.setUsuarioHistorico(UsuarioHistorico.builder()
                .descricao("Ativação de usuário.")
                .build()
        );

        MessageHeaderAccessor messageHeaderAccessor = new MessageHeaderAccessor();
        messageHeaderAccessor.setHeader("dto", dto);

        stateMachineEventService.sendEvent(usuario, events, messageHeaderAccessor);

        return "Usuario Ativado com sucesso!";
    }

    @Override
    public String suspender(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .id(dto.id())
                .email(dto.email())
                .idStatus(dto.idstatus())
                .build();
        UsuarioEvents events = UsuarioEvents.SUSPENDER;

        events.setUsuarioHistorico(UsuarioHistorico.builder().descricao("Suspensão de usuário.").build());

        MessageHeaderAccessor messageHeaderAccessor = new MessageHeaderAccessor();
        stateMachineEventService.sendEvent(usuario, events, messageHeaderAccessor);
        return "Usuario Suspenso com sucesso!";
    }

    @Override
    public String inativar(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .id(dto.id())
                .email(dto.email())
                .idStatus(dto.idstatus())
                .build();
        UsuarioEvents events = UsuarioEvents.INATIVAR;

        events.setUsuarioHistorico(UsuarioHistorico.builder()
                .descricao("Inativação de usuário.")
                .build()
        );

        MessageHeaderAccessor messageHeaderAccessor = new MessageHeaderAccessor();
        stateMachineEventService.sendEvent(usuario, events, messageHeaderAccessor);
        return "Usuario Inativado com sucesso!";
    }

    @Override
    public String excluir(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .id(dto.id())
                .email(dto.email())
                .idStatus(dto.idstatus())
                .build();
        UsuarioEvents events = UsuarioEvents.EXCLUIR;

        events.setUsuarioHistorico(UsuarioHistorico.builder()
                .descricao("Excusão de usuário.")
                .build()
        );

        MessageHeaderAccessor messageHeaderAccessor = new MessageHeaderAccessor();
        stateMachineEventService.sendEvent(usuario, events, messageHeaderAccessor);
        return "Usuario Excluido com sucesso!";
    }
}
