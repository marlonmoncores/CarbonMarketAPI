package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.to.MarketBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.BuyOutputTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/market", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketController {

    @PostMapping(path = "/user/buy")
    public ResponseEntity<BuyOutputTO> saveUserBuy(@RequestBody MarketBuyInputTO marketBuyInputTO, @RequestHeader(name = "idMarket") Integer idMarket){
        BuyOutputTO buyOutputTO = new BuyOutputTO();
        buyOutputTO.setGradeghg("A");
        buyOutputTO.setTotalghg(12345);
        return ResponseEntity.ok().body(buyOutputTO);
    }
}
