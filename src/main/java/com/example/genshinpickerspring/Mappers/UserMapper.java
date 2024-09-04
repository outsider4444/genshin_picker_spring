package com.example.genshinpickerspring.Mappers;

import com.example.genshinpickerspring.Models.RequestModels.UserInfo;
import com.example.genshinpickerspring.Models.RequestModels.UserTournament;
import com.example.genshinpickerspring.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserInfo convertToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setUserCharacters(user.getUserHeroes());
        userInfo.setUsername(user.getUsername());
        userInfo.setRole(user.getRole());
        userInfo.setOwnedTournaments(user.getOwnedTournaments());
        return userInfo;
    }

    public UserTournament convertToUserTournament(User user) {
        UserTournament userTournament = new UserTournament();
        userTournament.setId(user.getId());
        userTournament.setEmail(user.getEmail());
        userTournament.setRole(user.getRole());
        userTournament.setUsername(user.getUsername());
        return userTournament;
    }

    // Другие методы маппинга могут быть здесь
}
