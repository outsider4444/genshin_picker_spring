package com.example.genshinpickerspring.Services;


import com.example.genshinpickerspring.Config.JwtService;
import com.example.genshinpickerspring.Models.User;
import com.example.genshinpickerspring.Models.RequestModels.UserInfo;
import com.example.genshinpickerspring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public Optional<UserInfo> getUserInfoByToken(String token) {
        try {
            String jwt = extractJwtFromHeader(token);  // Extract JWT
            String username = jwtService.getUsernameFromToken(jwt);

            User userDetails = userRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            UserInfo userInfo = convertToUserInfoModel(userDetails);  // Convert to UserInfoModel
            return Optional.of(userInfo);
        } catch (Exception e) {
            // Логирование ошибки (например, используя Logger)
            return Optional.empty();
        }
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    private String extractJwtFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Remove "Bearer " prefix
        }
        throw new IllegalArgumentException("Invalid Authorization header");
    }

    private UserInfo convertToUserInfoModel(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setUserCharacters(user.getUserHeroes());
        userInfo.setUsername(user.getUsername());
        userInfo.setRole(user.getRole());
        userInfo.setOwnedTournaments(user.getOwnedTournaments());
        return userInfo;
    }
}
