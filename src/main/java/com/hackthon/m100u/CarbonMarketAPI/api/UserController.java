package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping(path = "/user")
    public ResponseEntity<UserOutputTO> createUser(@RequestBody UserInputTO userInputTO){
        UserOutputTO createdUser = userFacade.createUser(userInputTO);
        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping("/me")
    public ResponseEntity<UserOutputTO> retrieveUser(Principal principal){
        UserOutputTO createdUser = userFacade.findUserById(Long.parseLong(principal.getName()));
        return ResponseEntity.ok().body(createdUser);
    }

    @PostMapping(path = "/user/buy")
    public ResponseEntity<UserBuyInputTO> saveUserBuy(@RequestBody UserBuyInputTO userBuyInputTO){
        //UserOutputTO createdUser = userFacade.createUser(userInputTO);
        return ResponseEntity.ok().body(userBuyInputTO);
    }
}
