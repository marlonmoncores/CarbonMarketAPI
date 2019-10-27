package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.Market;
import com.hackthon.m100u.CarbonMarketAPI.model.MarketRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.entity.MarketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateMarketService {

    @Autowired
    MarketRepository marketRepository;

    public Market execute(Market market){
        MarketEntity marketCreated = marketRepository.findByName(market.getName()).orElseGet(()-> {
            MarketEntity marketEntity = toEntity(market);
            marketRepository.save(marketEntity);
            return marketEntity;
        });
        return marketCreated.toMarket();
    }

    private MarketEntity toEntity(Market market){
        MarketEntity marketEntity = new MarketEntity();
        marketEntity.setName(market.getName());
        marketEntity.setCreatedAt(market.getCreatedAt() != null ? market.getCreatedAt() : new Date());
        return marketEntity;
    }


}
