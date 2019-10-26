package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import com.hackthon.m100u.CarbonMarketAPI.domain.User;
import com.hackthon.m100u.CarbonMarketAPI.model.ItemRepository;
import com.hackthon.m100u.CarbonMarketAPI.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetrieveItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> execute(){
        return itemRepository.findAll().stream().map(item -> item.toItem()).collect(Collectors.toList());
    }
}
