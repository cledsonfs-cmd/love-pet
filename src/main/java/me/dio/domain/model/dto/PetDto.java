package me.dio.domain.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

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
    private UserDto user;
    private AdopterDto adopter;
    private List<ImageDto> images;
}
