package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.security.JWTHelper;
import com.hackthon.m100u.CarbonMarketAPI.api.security.JwtConfig;
import com.hackthon.m100u.CarbonMarketAPI.api.to.AuthInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.AuthOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.AuthUserService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.CreateUserService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.RetrieveUserService;
import io.jsonwebtoken.JwtHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class UserFacade {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private RetrieveUserService retrieveUserService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private JwtConfig jwtConfig;

    public UserOutputTO createUser(UserInputTO userInputTO){
        User user = userInputTO.toUser();
        User created = createUserService.execute(user);
        return new UserOutputTO(created);
    }

    public AuthOutputTO authUser(AuthInputTO authInputTO) {
        Optional<User> userOptional = authUserService.execute(authInputTO.getCpf(), authInputTO.getPassword());
        if(userOptional.isPresent()){
            AuthOutputTO authOutputTO = new AuthOutputTO();
            String jwt = jwtConfig.getPrefix() + JWTHelper.createToken(jwtConfig,userOptional.get().getId()+"", Arrays.asList("user"));
            authOutputTO.setAuthorization(jwt);
            return authOutputTO;
        }
        return null;
    }

    public UserOutputTO findUserById(long userId) {
        return new UserOutputTO(retrieveUserService.findById(userId));
    }
}
