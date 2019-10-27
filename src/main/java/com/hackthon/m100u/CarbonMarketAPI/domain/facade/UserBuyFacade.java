package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.*;
import com.hackthon.m100u.CarbonMarketAPI.client.ReceitaFederalClient;
import com.hackthon.m100u.CarbonMarketAPI.domain.*;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.CalculateCarbonConsumptionService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.CreateMarketService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.ReadUserBuy;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.SaveUserBuy;
import com.hackthon.m100u.CarbonMarketAPI.model.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserBuyFacade {

    @Autowired
    private SaveUserBuy saveUserBuy;

    @Autowired
    private ReadUserBuy readUserBuy;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CalculateCarbonConsumptionService calculateCarbonConsumptionService;

    @Autowired
    private CreateMarketService createMarketService;

    @Transactional
    public BuyOutputTO saveUserBuy(UserBuyInputTO userBuyInputTO) {
        UserPurchase userPurchase = userBuyInputTO.toUserPurchase();
        long purchaseId = saveUserBuy.execute(userPurchase);
        BuyOutputTO buyOutputTO = calculateBuyCost(userPurchase);
        buyOutputTO.setId(purchaseId);
        return buyOutputTO;
    }

    @Transactional
    public BuyOutputTO saveUserBuy(MarketBuyInputTO marketBuyInputTO) {
        UserPurchase userPurchase = marketBuyInputTO.toUserPurchase();
        long purchaseId = saveUserBuy.execute(marketBuyInputTO.toUserPurchase());
        BuyOutputTO buyOutputTO = calculateBuyCost(userPurchase);
        buyOutputTO.setId(purchaseId);
        return buyOutputTO;
    }

    @Transactional
    public BuyOutputTO saveUserCodeBuy(UserBuyCodeInputTO userBuyCodeInputTO) {
        ReceitaFederalClient receitaFederalClient = new ReceitaFederalClient(itemRepository);

        UserPurchase userPurchase = new UserPurchase();
        Invoice invoice = receitaFederalClient.readPage(userBuyCodeInputTO.getUrl());
        Market market = createMarketService.execute(invoice.getMarket());
        userPurchase.setMarket(market);
        userPurchase.setCreatedAt(invoice.getEmission_date());
        User user = new User();
        user.setId(userBuyCodeInputTO.getIdUser());
        userPurchase.setUser(user);
        userPurchase.setItems(
            invoice.getRecords().stream().map(item -> {
                ItemPurchase itemPurchase = new ItemPurchase();
                itemPurchase.setItem(item.getItem());
                itemPurchase.setQuantity(item.getQuantity());
                return itemPurchase;
            }).collect(Collectors.toList())
        );

        long purchaseId = saveUserBuy.execute(userPurchase);
        BuyOutputTO buyOutputTO = calculateBuyCost(userPurchase);
        buyOutputTO.setId(purchaseId);
        return buyOutputTO;


    }

    private BuyOutputTO calculateBuyCost(UserPurchase userPurchase) {
        BuyOutputTO buyOutputTO = new BuyOutputTO();
        buyOutputTO.setConsumption(calculateCarbonConsumptionService.calculateTotalConsumption(userPurchase));

        return buyOutputTO;
    }


    public Optional<UserPurchase> getUserBuy(long userId, Long buyId) {
        Optional<UserPurchase> userPurchase = readUserBuy.findByBuyId(userId, buyId);
        return userPurchase;
    }

    public List<UserPurchase> getUserBuyFiltered(UserBuyFilterTO userBuyFilterTO) {
        List<UserPurchase> userPurchases = readUserBuy.findByInterval(userBuyFilterTO.getUserId(), userBuyFilterTO.getBeginDate(), userBuyFilterTO.getEndDate());
        return userPurchases;
    }
}
