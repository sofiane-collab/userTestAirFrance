
package com.example.user.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user.backend.dto.UserDTO;
import com.example.user.backend.mapper.UserMapper;
import com.example.user.backend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @PostMapping("/register")
    @Operation(description = "register a new user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            long startTime = System.currentTimeMillis();
            UserDTO registeredUserDTO = userService.registerUser(userDTO);
            long endTime = System.currentTimeMillis();
            System.out.println("Processing time: " + (endTime - startTime) + "ms");
            return new ResponseEntity<>(registeredUserDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        Optional<UserDTO> userDTO = userService.getUserById(id);
        long endTime = System.currentTimeMillis();
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        return userDTO.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
