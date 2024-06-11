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
    
    final private String COUNTRY = "France";

    private UserMapper userMapper = UserMapper.INSTANCE;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        if (user != null && ((user.getAge() != null && user.getAge() <= 18) || !COUNTRY.equalsIgnoreCase(user.getCountry()))) {
            throw new IllegalArgumentException("Only adults ( age > 18 years)  and that live in France can create an account");
        }
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO);
    }
}

