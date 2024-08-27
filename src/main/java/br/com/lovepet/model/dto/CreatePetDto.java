package br.com.lovepet.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreatePetDto {

    private String name;
    private String age;
    private String description;
    private String species;
    private String breed;
    private String color;
    private boolean available;
    private LocalDateTime register;
    private Integer idUsuario;
    private List<CreateImageDto> images;
}
