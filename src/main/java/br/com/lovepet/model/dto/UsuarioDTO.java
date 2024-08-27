package br.com.lovepet.model.dto;

import br.com.lovepet.model.entity.Role;

public record UsuarioDTO(Integer id,
                         String email,
                         String nome,
                         String provedor,
                         String imageUrl,
                         Integer idstatus,
                         Role role) {
}
