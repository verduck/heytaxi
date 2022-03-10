package com.moca.heytaxi.controller;

import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.UserDTO;
import com.moca.heytaxi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO.Response> loadMe(@AuthenticationPrincipal User user) {
        UserDTO.Response response = new UserDTO.Response();
        response.setSuccess(true);
        response.setMessage("사용자 정보를 성공적으로 불러왔습니다.");
        response.setUser(modelMapper.map(user, UserDTO.class));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UserDTO.Response> putMe(@AuthenticationPrincipal User user, @RequestBody UserDTO.Request request) {
        UserDTO.Response response = new UserDTO.Response();
        System.out.println(request.getUsername());
        System.out.println(request.getName());
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getName() != null) {
            user.setName((request.getName()));
        }
        user = userService.updateUser(user);
        response.setSuccess(true);
        response.setMessage("사용자 정보를 성공적으로 변경하였습니다.");
        response.setUser(modelMapper.map(user, UserDTO.class));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<UserDTO.Response> deleteMe(@AuthenticationPrincipal User user) {
        UserDTO.Response response = new UserDTO.Response();
        userService.deleteUser(user);
        response.setSuccess(true);
        response.setMessage("사용자 정보를 성공적으로 제거하였습니다.");
        return ResponseEntity.ok(response);
    }
}
