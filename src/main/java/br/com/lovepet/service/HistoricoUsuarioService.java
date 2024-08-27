package br.com.lovepet.service;

import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.model.entity.UsuarioHistorico;

import java.util.List;

public interface HistoricoUsuarioService {

    String salvar(UsuarioHistorico historicoUsuario);

    UsuarioHistorico buscarPorId(Integer id);

    List<UsuarioHistorico> buscarTodos();

    String excluir(Integer id);

    String atualizar(UsuarioHistorico historicoUsuario);

    List<UsuarioHistorico> buscarPorUsuario(Usuario usuario);

}
