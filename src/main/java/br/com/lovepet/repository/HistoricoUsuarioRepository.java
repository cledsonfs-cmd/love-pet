package br.com.lovepet.repository;

import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.model.entity.UsuarioHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoUsuarioRepository extends JpaRepository<UsuarioHistorico, Integer> {

    List<UsuarioHistorico> findByUsuario(Usuario usuario);
}
