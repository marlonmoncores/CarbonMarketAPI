package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.utils.PasswordUtils;
import com.hackthon.m100u.CarbonMarketAPI.model.UserRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.UserSystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> execute(String cpf, String password){
        Optional<UserSystemEntity> userSystemEntityOptional = userRepository.findByCpf(cpf);
        if(userSystemEntityOptional.isPresent()){
            long ts = userSystemEntityOptional.get().getCreatedAt().getTime();
            String saltedPath = new PasswordUtils().saltPassword(ts,password);
            if(saltedPath.equalsIgnoreCase(userSystemEntityOptional.get().getPassword())) {
                return Optional.of(userSystemEntityOptional.get().toUser());
            }
        }
        return Optional.empty();
    }

}
