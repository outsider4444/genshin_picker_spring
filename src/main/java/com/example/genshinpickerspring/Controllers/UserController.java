package com.example.genshinpickerspring.Controllers;

import com.example.genshinpickerspring.Config.JwtService;
import com.example.genshinpickerspring.Models.Role;
import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Models.UserCharacterList;
import com.example.genshinpickerspring.Models.UserInfo;
import com.example.genshinpickerspring.Repositories.UserRepository;
import com.example.genshinpickerspring.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<UserInfo> getUserInfo(@RequestHeader("Authorization") String token) {
        return userService.getUserInfoByToken(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));  // Возврат 404, если пользователь не найден
    }
}
