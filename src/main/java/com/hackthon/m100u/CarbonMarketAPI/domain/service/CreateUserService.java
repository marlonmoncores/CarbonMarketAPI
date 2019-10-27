package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.utils.PasswordUtils;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.model.UserRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserSystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    public User execute(User user){
        UserSystemEntity userCreated = userRepository.findByCpf(user.getCpf()).orElseGet(()-> {
            UserSystemEntity userSystemEntity = toEntity(user);
            userRepository.save(userSystemEntity);
            return userSystemEntity;
        });
        return userCreated.toUser();
    }

    private UserSystemEntity toEntity(User user){
        UserSystemEntity userSystemEntity = new UserSystemEntity();
        Date now = new Date();
        userSystemEntity.setPassword(new PasswordUtils().saltPassword(now.getTime(),user.getPassword()));
        userSystemEntity.setCpf(user.getCpf());
        userSystemEntity.setEmail(user.getEmail());
        userSystemEntity.setCreatedAt(now);
        return userSystemEntity;
    }


}
