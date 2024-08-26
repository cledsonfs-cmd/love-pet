package me.dio.domain.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDto {

    private Long id;
    private String foto;
    private LocalDateTime register;
}
