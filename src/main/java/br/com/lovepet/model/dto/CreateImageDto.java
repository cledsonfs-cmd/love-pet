package br.com.lovepet.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateImageDto {

    private String foto;
    private LocalDateTime register;
}
