package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.to.BuyOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.MarketBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.UserBuyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/market", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketController {

    @Autowired
    private UserBuyFacade userBuyFacade;

    @PostMapping(path = "/user/buy")
    public ResponseEntity<BuyOutputTO> saveUserBuy(@RequestBody MarketBuyInputTO marketBuyInputTO, @RequestHeader(name = "idMarket") Long idMarket){
        marketBuyInputTO.setIdMarket(idMarket);
        BuyOutputTO response = userBuyFacade.saveUserBuy(marketBuyInputTO);
        return ResponseEntity.ok().body(response);
    }
}
