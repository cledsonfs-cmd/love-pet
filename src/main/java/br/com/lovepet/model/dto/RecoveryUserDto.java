package br.com.lovepet.model.dto;

import br.com.lovepet.model.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        Integer idstatus,
        List<Role> roles) {
}
