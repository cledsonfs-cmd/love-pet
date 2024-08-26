package me.dio.domain.service;

import me.dio.domain.model.entity.Pet;
import me.dio.domain.model.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUser();
    User saveUser(User user);
    String deleteUser(Long id);
}
