package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.to.AuthInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.AuthOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    public ResponseEntity<AuthOutputTO> authUser(@RequestBody AuthInputTO authInputTO){
        AuthOutputTO foundAuth = userFacade.authUser(authInputTO);
        if(foundAuth == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok()
                .header("Authorization",foundAuth.getAuthorization()).body(foundAuth);
    }
}
