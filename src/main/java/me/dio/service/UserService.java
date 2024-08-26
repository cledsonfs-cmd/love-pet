package me.dio.service;

import me.dio.domain.model.dto.UserDto;
import me.dio.domain.model.entity.Pet;
import me.dio.domain.model.entity.User;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    List<UserDto> getAllUser();
    UserDto saveUser(UserDto dto);
    String deleteUser(Long id);
}
