package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.RetrieveItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ItemFacade {

    @Autowired
    private RetrieveItemService retrieveItemService;

    @Transactional
    public List<Item> retrieveAllItens(){
        return retrieveItemService.execute();
    }
}
