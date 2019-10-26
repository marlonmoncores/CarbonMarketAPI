package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.to.UserInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    public ResponseEntity<UserOutputTO> createUser(@RequestBody UserInputTO userInputTO){
        UserOutputTO createdUser = userFacade.createUser(userInputTO);
        return ResponseEntity.ok().body(createdUser);
    }
}
