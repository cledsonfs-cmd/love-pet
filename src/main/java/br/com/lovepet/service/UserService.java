package br.com.lovepet.service;

import br.com.lovepet.model.dto.CreateUserDto;
import br.com.lovepet.model.dto.LoginRequestDTO;
import br.com.lovepet.model.dto.RecoveryJwtTokenDto;
import br.com.lovepet.model.dto.UsuarioDTO;
import br.com.lovepet.model.entity.Usuario;

import java.util.List;

public interface UserService {

    RecoveryJwtTokenDto authenticateUser(LoginRequestDTO loginRequestDTO);

    Usuario createUser(CreateUserDto createUserDto) ;

    String atualizarUser(UsuarioDTO usuarioDTO);

    Usuario findById(Integer id);

    List<Usuario> findAll();

    Usuario obterPorEmail(String email);

    String ativar(UsuarioDTO dto);

    String suspender(UsuarioDTO dto);

    String inativar(UsuarioDTO dto);

    String excluir(UsuarioDTO dto);
}
