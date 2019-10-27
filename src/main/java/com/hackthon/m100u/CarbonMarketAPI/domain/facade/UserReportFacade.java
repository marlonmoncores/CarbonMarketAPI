package com.hackthon.m100u.CarbonMarketAPI.domain.facade;

import com.hackthon.m100u.CarbonMarketAPI.api.to.UserMonthlyReportInputTO;
import com.hackthon.m100u.CarbonMarketAPI.api.to.UserMonthlyReportOutputTO;
import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import com.hackthon.m100u.CarbonMarketAPI.domain.ItemPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.CalculateCarbonConsumptionService;
import com.hackthon.m100u.CarbonMarketAPI.domain.service.ReadUserBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserReportFacade {

    @Autowired
    private ReadUserBuy readUserBuy;

    @Autowired
    private CalculateCarbonConsumptionService calculateCarbonConsumptionService;


    public UserMonthlyReportOutputTO createMonthlyReport(UserMonthlyReportInputTO userMonthlyReportInputTO) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date begin = dateFormat.parse(userMonthlyReportInputTO.getYear()+"-"+userMonthlyReportInputTO.getMonth()+"-01");
            Date end = dateFormat.parse(userMonthlyReportInputTO.getYear()+"-"+(1+userMonthlyReportInputTO.getMonth())+"-01");
            List<UserPurchase> userPurchaseList = readUserBuy.findByInterval(userMonthlyReportInputTO.getIdUser(),begin,end);
            UserMonthlyReportOutputTO reportOutputTO = new UserMonthlyReportOutputTO();

            List<ItemPurchase> originalItems = convertToItemPurchase(userPurchaseList);
            Consumption carbonConsumption = calculateCarbonConsumptionService.calculateTotalConsumptionWithItem(originalItems);
            reportOutputTO.setOriginal(new UserMonthlyReportOutputTO.UserMonthlyReportOutputInternalTO(originalItems, carbonConsumption));

            List<ItemPurchase> suggestedItems = calculateCarbonConsumptionService.suggestDiet(userPurchaseList);
            Consumption suggestedItemsConsumption = calculateCarbonConsumptionService.calculateTotalConsumptionWithItem(suggestedItems);
            reportOutputTO.setSuggestion(new UserMonthlyReportOutputTO.UserMonthlyReportOutputInternalTO(suggestedItems, suggestedItemsConsumption));



            return reportOutputTO;
        } catch (ParseException e) {
            throw new RuntimeException("ERROR CONVERTING DATE");
        }
    }

    private List<ItemPurchase> convertToItemPurchase(List<UserPurchase> userPurchaseList){
        Map<Long,ItemPurchase> itemQuantityMap = new HashMap<>();
        userPurchaseList.stream().forEach(userPurchase ->
            userPurchase.getItems().stream().forEach(item ->{
                ItemPurchase current = itemQuantityMap.getOrDefault(item.getItem().getId(), new ItemPurchase());
                current.setQuantity(current.getQuantity()+item.getQuantity());
                current.setItem(item.getItem());
                itemQuantityMap.putIfAbsent(item.getItem().getId(),current);
            }));
        return itemQuantityMap.values().stream().collect(Collectors.toList());
    }
}
