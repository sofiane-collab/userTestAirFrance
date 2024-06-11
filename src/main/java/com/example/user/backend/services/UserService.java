package com.example.user.backend.services;

import com.example.user.backend.dto.UserDTO;
import com.example.user.backend.entities.User;
import com.example.user.backend.mapper.UserMapper;
import com.example.user.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper = UserMapper.INSTANCE;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        if (user != null && ((user.getAge() != null && user.getAge() <= 18) || !"France".equalsIgnoreCase(user.getCountry()))) {
            throw new IllegalArgumentException("Only adults and users from France can register.");
        }
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(String id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO);
    }
}

