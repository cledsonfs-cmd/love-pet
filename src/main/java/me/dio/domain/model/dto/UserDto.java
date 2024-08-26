package me.dio.domain.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String image;
    private String phone;
    private LocalDateTime register;
}
