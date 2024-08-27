package br.com.lovepet.model.dto;

import br.com.lovepet.enums.RoleName;

public record UpdateUserDto(
        Integer id,
        String email,
        String nome,
        String password,
        Integer idstatus,
        RoleName role) {
}
