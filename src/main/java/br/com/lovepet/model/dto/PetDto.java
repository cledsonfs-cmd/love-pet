package br.com.lovepet.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import br.com.lovepet.model.dto.UsuarioDTO;
import br.com.lovepet.model.dto.ImageDto;

@Data
public class PetDto {

    private Long id;
    private String name;
    private String age;
    private String description;
    private String species;
    private String breed;
    private String color;
    private boolean available;
    private LocalDateTime register;
    private UsuarioDTO user;
    private UsuarioDTO adopter;
    private List<ImageDto> images;
}
