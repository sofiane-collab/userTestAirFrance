package com.example.user.backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.user.backend.dto.UserDTO;
import com.example.user.backend.entities.User;
import com.example.user.backend.repository.UserRepository;
import com.example.user.backend.services.UserService;
import com.example.user.backend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(20);
        userDTO.setCountry("France");

        User user = new User();

        when(userMapper.userDTOToUser(any(UserDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userService.registerUser(userDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId("1");

        UserDTO userDTO = new UserDTO();
        userDTO.setId("1");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userMapper.userToUserDTO(any(User.class))).thenReturn(userDTO);

        Optional<UserDTO> result = userService.getUserById("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
    }
}
