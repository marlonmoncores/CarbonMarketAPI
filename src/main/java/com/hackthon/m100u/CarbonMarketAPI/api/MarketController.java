package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.api.to.MarketBuyInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.MarketBuyOutputTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/market", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketController {

    @PostMapping(path = "/user/buy")
    public ResponseEntity<MarketBuyOutputTO> saveUserBuy(@RequestBody MarketBuyInputTO marketBuyInputTO, @RequestHeader(name = "idMarket") Integer idMarket){
        MarketBuyOutputTO marketBuyOutputTO = new MarketBuyOutputTO();
        marketBuyOutputTO.setGradeghg("A");
        marketBuyOutputTO.setTotalghg(12345);
        return ResponseEntity.ok().body(marketBuyOutputTO);
    }
}
