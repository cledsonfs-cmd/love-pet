package br.com.lovepet.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDto {

    private Long id;
    private String foto;
    private LocalDateTime register;
}
