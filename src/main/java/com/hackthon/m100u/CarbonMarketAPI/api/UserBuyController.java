package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.helper.PrincipalHelper;
import com.hackthon.m100u.CarbonMarketAPI.api.to.BuyOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyCodeInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyFilterTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserBuyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserBuyController {

    @Autowired
    private UserBuyFacade userBuyFacade;

    @PostMapping(path = "/user/buy")
    public ResponseEntity<BuyOutputTO> saveUserBuy(@RequestBody UserBuyInputTO userBuyInputTO, Principal principal){
        userBuyInputTO.setIdUser(PrincipalHelper.getUser(principal));
        BuyOutputTO response = userBuyFacade.saveUserBuy(userBuyInputTO);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/user/buy/code")
    public ResponseEntity<BuyOutputTO> saveUserCodeBuy(@RequestBody UserBuyCodeInputTO userBuyCodeInputTO, Principal principal){
        userBuyCodeInputTO.setIdUser(PrincipalHelper.getUser(principal));
        BuyOutputTO response = userBuyFacade.saveUserCodeBuy(userBuyCodeInputTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/user/buy/{id}")
    public ResponseEntity<UserPurchase> getUserBy(@PathVariable("id") Long buyId, Principal principal){
        Optional<UserPurchase> userPurchaseOptional = userBuyFacade.getUserBuy(PrincipalHelper.getUser(principal), buyId);
        if(userPurchaseOptional.isPresent()) {
            return ResponseEntity.ok().body(userPurchaseOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/user/buy/filter")
    public ResponseEntity<List<UserPurchase>> getUserBuyFiltered(@RequestBody UserBuyFilterTO userBuyFilterTO, Principal principal){
        userBuyFilterTO.setUserId(PrincipalHelper.getUser(principal));
        List<UserPurchase> userPurchases = userBuyFacade.getUserBuyFiltered(userBuyFilterTO);
        if(! userPurchases.isEmpty()) {
            return ResponseEntity.ok().body(userPurchases);
        }
        return ResponseEntity.notFound().build();
    }
}
