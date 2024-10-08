package br.com.lovepet.impl;

import br.com.lovepet.enums.RoleName;
import br.com.lovepet.enums.UsuarioStates;
import br.com.lovepet.model.UserDetailsImpl;
import br.com.lovepet.model.dto.CreateUserDto;
import br.com.lovepet.model.dto.LoginRequestDTO;
import br.com.lovepet.model.dto.RecoveryJwtTokenDto;
import br.com.lovepet.model.dto.UsuarioDTO;
import br.com.lovepet.model.entity.Role;
import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.repository.UsuarioRepository;
import br.com.lovepet.security.SecurityConfiguration;
import br.com.lovepet.security.service.JwtTokenService;
import br.com.lovepet.service.impl.UserServiceImpl;
import br.com.lovepet.statemachine.service.StateMachineEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    AuthenticationManager mockAuthenticationManager;

    @Mock
    JwtTokenService mockJwtTokenService;

    @Mock
    UsuarioRepository mockUsuarioRepository;

    @Mock
    SecurityConfiguration mockSecurityConfiguration;

    @Mock
    StateMachineEventService mockStateMachineEventService;

    @InjectMocks
    UserServiceImpl injectUserService;

    Usuario mockUsuario;
    LoginRequestDTO mockLoginRequestDTO;
    UsuarioDTO mockUsuarioDTO;
    CreateUserDto mockCreateUserDTO;
    Authentication mockAuthentication;
    UserDetailsImpl mockUserDetails;

    @BeforeEach
    void setUp() {
        mockUsuario = Usuario.builder()
                .id(1)
                .email("andre@email.com")
                .nome("Andre da Silva")
                .idStatus(UsuarioStates.INICIO.getId())
                .password("andre123456")
                .role(Role.builder()
                        .name(RoleName.ROLE_ADMINISTRATOR)
                        .build())
                .build();

        mockLoginRequestDTO = new LoginRequestDTO("andre@email.com", "andre123456");
        Role RoleName = Role.builder()
                .id(1L)
                .name(br.com.lovepet.enums.RoleName.ROLE_ADMINISTRATOR)
                .build();
        mockUsuarioDTO = new UsuarioDTO(1976, "andre@email.com", "andre", "","",UsuarioStates.ATIVO.getId(),RoleName);

        mockCreateUserDTO = new CreateUserDto("paulo@email.com", "paulo","paulo123456",UsuarioStates.ATIVO.getId(),RoleName.getName());
        mockAuthentication = new Authentication() {
            @Override
            public String getName() {
                return "";
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return mockUserDetails;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }
        };
        mockUserDetails = new UserDetailsImpl(mockUsuario);

    }

    @DisplayName("JUnit test autenticar um usuario existente retornando um token de acesso")
    @Test
    void testAuthenticateUser() {
        String expectMessage = "$%¨&token_gerado&*()";
        when(mockUsuarioRepository.findByEmail(any())).thenReturn(Optional.of(mockUsuario));
        when(mockAuthenticationManager.authenticate(any())).thenReturn(mockAuthentication);
        when(mockJwtTokenService.generateToken(any(UserDetailsImpl.class))).thenReturn("$%¨&token_gerado&*()");

        RecoveryJwtTokenDto retorno = injectUserService.authenticateUser(mockLoginRequestDTO);

        assertNotNull(retorno);
        assertEquals(expectMessage, retorno.token());
    }

    @DisplayName("JUnit test autenticar usuario com um email vazio")
    @Test
    void testAuthenticateUserComEmailVazio() {
        String expectMessage = "username cannot be empty!";
        mockLoginRequestDTO = new LoginRequestDTO("", "andre123456");

        RuntimeException exception = assertThrows(RuntimeException.class, () ->  injectUserService.authenticateUser(mockLoginRequestDTO));

        assertEquals(expectMessage, exception.getMessage());
    }

    @DisplayName("JUnit test autenticar usuario com uma senha vazia")
    @Test
    void testAuthenticateUserComSenhaVazia() {
        String expectMessage = "password cannot be empty!";
        mockLoginRequestDTO = new LoginRequestDTO("andre@email.com", "");

        RuntimeException exception = assertThrows(RuntimeException.class, () ->  injectUserService.authenticateUser(mockLoginRequestDTO));

        assertEquals(expectMessage, exception.getMessage());
    }

    @DisplayName("JUnit test autenticar usuario email inválido")
    @Test
    void testAuthenticateUserEmailInvalido() {
        String expectMessage = "username not found!";

        when(mockUsuarioRepository.findByEmail(any())).thenReturn(Optional.ofNullable(null));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->  injectUserService.authenticateUser(mockLoginRequestDTO));

        assertEquals(expectMessage, exception.getMessage());
    }

    @DisplayName("JUnit test autenticar usuario com senha incorreta")
    @Test
    void testAuthenticateSenhaIncorreta() {
        String expectMessage = "username not found!";
        mockLoginRequestDTO = new LoginRequestDTO("andre@email.com", "xxxxx");
        when(mockUsuarioRepository.findByEmail(any())).thenReturn(Optional.of(mockUsuario));
        when(mockAuthenticationManager.authenticate(any())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->  injectUserService.authenticateUser(mockLoginRequestDTO));

        assertEquals(expectMessage, exception.getMessage());
    }

    @DisplayName("JUnit test criar um novo usuario")
    @Test
    void testCreateUser() {
        String expectMessage = "Usuario criado com sucesso!";
        when(mockUsuarioRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(mockSecurityConfiguration.passwordEncoder()).thenReturn(new BCryptPasswordEncoder());

        Usuario retorno = injectUserService.createUser(mockCreateUserDTO);

        assertNotNull(retorno);
    }

    @DisplayName("JUnit test atualizar um usuario existente")
    @Test
    void testAtualizarUser() {
        String expectMessage = "Usuario atualizado com sucesso!";
        when(mockUsuarioRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(mockUsuario));

        String retorno = injectUserService.atualizarUser(mockUsuarioDTO);

        assertEquals(expectMessage, retorno);
    }

    @DisplayName("JUnit test obter um usuario através de um id fornecido")
    @Test
    void testFindById() {
        when(mockUsuarioRepository.findById(anyInt())).thenReturn(Optional.ofNullable(mockUsuario));

        Usuario retorno = injectUserService.findById(1);

        assertEquals(mockUsuario, retorno);
        assertEquals(1, retorno.getId());
    }

    @DisplayName("JUnit test obter uma lista de usuarios existentes")
    @Test
    void testFindAll() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(mockUsuario);
        usuarios.add(mockUsuario);
        usuarios.add(mockUsuario);
        when(mockUsuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> retorno = injectUserService.findAll();

        assertFalse(retorno.isEmpty());
        assertEquals(3, retorno.size());
    }

    @DisplayName("JUnit test obter um usuario existente através de um e-mail fornecido")
    @Test
    void testObterPorEmail() {
        when(mockUsuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUsuario));

        Usuario retorno = injectUserService.obterPorEmail("andre@email.com");

        assertNotNull(retorno);

    }

    @DisplayName("JUnit test ativar um usuario existente utilizando a stateMachine")
    @Test
    void testAtivarUsuario() {
        String expectMessage = "Usuario Ativado com sucesso!";

        String retorno = injectUserService.ativar(mockUsuarioDTO);

        assertEquals(expectMessage, retorno);
    }

    @DisplayName("JUnit test suspender um usuario existente utilizando a stateMachine")
    @Test
    void testSuspender() {
        String expectMessage = "Usuario Suspenso com sucesso!";

        String retorno = injectUserService.suspender(mockUsuarioDTO);

        assertEquals(expectMessage, retorno);
    }

    @DisplayName("JUnit test inativar um usuario existente utilizando a stateMachine")
    @Test
    void testInativar() {
        String expectMessage = "Usuario Inativado com sucesso!";

        String retorno = injectUserService.inativar(mockUsuarioDTO);

        assertEquals(expectMessage, retorno);
    }

    @DisplayName("JUnit test excluir um usuario existente utilizando a stateMachine")
    @Test
    void testExcluir() {
        String expectMessage = "Usuario Excluido com sucesso!";

        String retorno = injectUserService.excluir(mockUsuarioDTO);

        assertEquals(expectMessage, retorno);
    }

}