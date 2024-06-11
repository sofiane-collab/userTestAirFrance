package com.example.user.backend;

import com.example.user.backend.dto.UserDTO;
import com.example.user.backend.entities.User;
import com.example.user.backend.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void testUserToUserDTO() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("toto");
        user.setLastName("coco");
        user.setAge(25);
        user.setCountry("France");

        UserDTO userDTO = userMapper.userToUserDTO(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getAge(), userDTO.getAge());
        assertEquals(user.getCountry(), userDTO.getCountry());
    }

    @Test
    public void testUserDTOToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("toto");
        userDTO.setLastName("coco");
        userDTO.setAge(25);
        userDTO.setCountry("France");

        User user = userMapper.userDTOToUser(userDTO);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getAge(), user.getAge());
        assertEquals(userDTO.getCountry(), user.getCountry());
    }
}

