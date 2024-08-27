package br.com.lovepet.enums;

import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.model.entity.UsuarioHistorico;
import lombok.Getter;
import lombok.Setter;

public enum UsuarioEvents {

    INICIAR,
    ATIVAR,
    SUSPENDER,
    INATIVAR,
    EXCLUIR;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private UsuarioHistorico usuarioHistorico;
}
