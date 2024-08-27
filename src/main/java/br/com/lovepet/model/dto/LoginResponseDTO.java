package br.com.lovepet.model.dto;

import br.com.lovepet.model.entity.Role;

public record LoginResponseDTO(
        Integer uuid,
        String email,
        String nome,
        RecoveryJwtTokenDto token,
        String provedor,
        String imageUrl,
        Role role
) {
}
