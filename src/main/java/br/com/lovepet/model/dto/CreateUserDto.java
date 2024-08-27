package br.com.lovepet.model.dto;

import br.com.lovepet.enums.RoleName;

public record CreateUserDto(
        String email,
        String nome,
        String password,
        Integer idstatus,
        RoleName role) {
}
