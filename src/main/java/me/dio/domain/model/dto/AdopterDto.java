package me.dio.domain.model.dto;

import jakarta.persistence.Entity;
import lombok.Data;
import me.dio.domain.model.entity.BaseUser;

import java.time.LocalDateTime;

@Data
public class AdopterDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String image;
    private String phone;
    private LocalDateTime register;
}
