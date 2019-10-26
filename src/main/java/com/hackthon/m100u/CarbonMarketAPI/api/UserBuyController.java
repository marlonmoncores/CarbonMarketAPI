package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.helper.PrincipalHelper;
import com.hackthon.m100u.CarbonMarketAPI.api.to.BuyOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserBuyFacade;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserBuyController {

    @Autowired
    private UserBuyFacade userBuyFacade;

    @PostMapping(path = "/user/buy")
    public ResponseEntity<BuyOutputTO> saveUserBuy(@RequestBody UserBuyInputTO userBuyInputTO, Principal principal){
        userBuyInputTO.setIdUser(PrincipalHelper.getUser(principal));
        userBuyFacade.saveUserBuy(userBuyInputTO);
        BuyOutputTO buyOutputTO = new BuyOutputTO();
        buyOutputTO.setGradeghg("A");
        buyOutputTO.setTotalghg(12345);
        return ResponseEntity.ok().body(buyOutputTO);
    }
}
