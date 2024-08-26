package me.dio.service.Impl;

import lombok.RequiredArgsConstructor;
import me.dio.domain.model.dto.UserDto;
import me.dio.domain.model.entity.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = repository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        modelMapper.map(users, userDtos);
        return userDtos;
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        repository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public String deleteUser(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new NoSuchElementException();
        }
        return "User excluído com sucesso!";
    }
}
