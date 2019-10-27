package com.hackthon.m100u.CarbonMarketAPI.api;

import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import com.hackthon.m100u.CarbonMarketAPI.domain.facade.ItemFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class ItemController {

    @Autowired
    private ItemFacade itemFacade;

    @GetMapping(path = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> createUser(@RequestHeader(name = "idMarket", required = false) Long idMarket){
        if(idMarket == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Item> itemList = itemFacade.retrieveAllItens();
        return ResponseEntity.ok().body(itemList);
    }
}
