package com.hackthon.m100u.CarbonMarketAPI.domain.service;

import com.hackthon.m100u.CarbonMarketAPI.domain.Consumption;
import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import com.hackthon.m100u.CarbonMarketAPI.domain.ItemCategory;
import com.hackthon.m100u.CarbonMarketAPI.domain.UserPurchase;
import com.hackthon.m100u.CarbonMarketAPI.model.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculateCarbonConsumptionService {

    @Autowired
    private ItemRepository itemRepository;


    private final Map<Long, ItemCategory> foodCategories = new HashMap<>();
    private final Map<Long, Item> foodData = new HashMap<>();


    public CalculateCarbonConsumptionService() {

    }

    private void fillData() {
        itemRepository.findAll().forEach(item -> {
            foodData.put(item.getId(), item.toItem());
            foodCategories.put(item.getCategory().getId(), item.getCategory().toCategory());
        });
    }

    public Consumption calculateTotalConsumption(UserPurchase userPurchase) {
        return calculateTotalConsumption(Collections.singletonList(userPurchase));
    }

    public Consumption calculateTotalConsumption(List<UserPurchase> userPurchaseList) {
        fillData();
        var foodConsumption = convertUserPurchaseToMap(userPurchaseList);
        return calculateTotalConsumption(foodConsumption);
    }

    public Map<Long, Integer> suggestDiet(List<UserPurchase> userPurchaseList) {
        fillData();
        double reductionTargetPercentage = 0.05;

        var foodConsumption = convertUserPurchaseToMap(userPurchaseList);
        double totalConsumption = calculateTotalConsumption(foodConsumption).getTotalghg();
        System.out.println(totalConsumption);
        double targetConsumption = totalConsumption * (1 - reductionTargetPercentage);
        balanceDiet(foodConsumption, targetConsumption);
        boolean changedOrStart = true;
        while (calculateTotalConsumption(foodConsumption).getTotalghg() > targetConsumption && changedOrStart) {
            changedOrStart = decrementExcessiveCategory(foodCategories.entrySet().stream().filter(fc -> fc.getValue().isOptional()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), foodData, foodConsumption);
        }
        changedOrStart = true;
        while (calculateTotalConsumption(foodConsumption).getTotalghg() > targetConsumption && changedOrStart) {
            changedOrStart = substituteWithinCategory(foodData.entrySet().stream()
                            .filter(fd -> foodConsumption.containsKey(fd.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                    , foodConsumption);
        }
        changedOrStart = true;
        while (calculateTotalConsumption(foodConsumption).getTotalghg() > targetConsumption && changedOrStart) {
            changedOrStart = substituteWithinCategory(foodData, foodConsumption);
        }
        return foodConsumption;
    }

    private HashMap<Long, Integer> convertUserPurchaseToMap(List<UserPurchase> userPurchaseList) {
        var foodConsumption = new HashMap<Long, Integer>();
        userPurchaseList.stream().forEach(userPurchase ->
                userPurchase.getItems().stream().forEach(item -> {
                    Integer currentQuantity = foodConsumption.get(item.getItem().getId());
                    if (currentQuantity == null) {
                        currentQuantity = 0;
                    }
                    foodConsumption.put(item.getItem().getId(), currentQuantity + item.getQuantity());
                })
        );
        return foodConsumption;
    }


    private void balanceDiet(HashMap<Long, Integer> sampleFoodConsumption, double targetConsumption) {
        boolean changedOrStart = true;
        while (calculateTotalConsumption(sampleFoodConsumption).getTotalghg() > targetConsumption && changedOrStart) {
            changedOrStart = decrementExcessiveCategory(foodCategories.entrySet().stream().filter(fc -> fc.getValue().isOptional()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), foodData, sampleFoodConsumption);
        }
        changedOrStart = true;
        while (calculateTotalConsumption(sampleFoodConsumption).getTotalghg() > targetConsumption && changedOrStart) {
            changedOrStart = decrementExcessiveCategory(foodCategories.entrySet().stream().filter(fc -> !fc.getValue().isOptional()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), foodData, sampleFoodConsumption);
        }
    }

    private boolean substituteWithinCategory(Map<Long, Item> alternativesFoodData, Map<Long, Integer> consumptionFoodItems) {
        for (var consumptionFoodItem : consumptionFoodItems.entrySet()) {
            var curAlternatives = alternativesFoodData.entrySet().stream()
                    .filter(afd -> afd.getValue().getCarbonServing() < alternativesFoodData.get(consumptionFoodItem.getKey()).getCarbonServing())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (curAlternatives.size() > 0) {
                var keysAsArray = new ArrayList<>(curAlternatives.keySet());
                Random r = new Random();
                var target_key = keysAsArray.get(r.nextInt(keysAsArray.size()));
                var target_item = consumptionFoodItems.entrySet().stream().filter(cfi -> cfi.getKey().equals(target_key)).findFirst();
                if (target_item.isPresent()) {
                    var alternative_item = target_item.get();
                    alternative_item.setValue(alternative_item.getValue() + 1);
                } else {
                    consumptionFoodItems.put(target_key, 1);
                }
                consumptionFoodItem.setValue(consumptionFoodItem.getValue() - 1);
                return true;
            }
        }
        return false;
    }

    private boolean decrementExcessiveCategory(Map<Long, ItemCategory> foodCategories, Map<Long, Item> foodData, Map<Long, Integer> consumptionFoodItems) {
        boolean changed = false;
        for (Long category_id : foodCategories.keySet()) {
            if (getTotalCategoryServings(foodData, consumptionFoodItems, category_id) > foodCategories.get(category_id).getDailyPortions() * 30
                    || foodCategories.get(category_id).isOptional()) {
                var sel_item = consumptionFoodItems.entrySet().stream()
                        .filter(s -> foodData.get(s.getKey()).getCategory().getId() == category_id)
                        .findFirst();
                if (sel_item.isPresent()) {
                    changed = true;
                    var item_modify = sel_item.get();
                    item_modify.setValue(item_modify.getValue() - 1);
                    if (item_modify.getValue() == 0)
                        consumptionFoodItems.remove(item_modify.getKey());
                }
            }
        }
        return changed;
    }

    private Consumption calculateTotalConsumption(Map<Long, Integer> consumptionFoodItems) {
        double carbonConsumpton = 0;
        double waterConsumption = 0;
        for (Map.Entry<Long, Integer> sampleItem : consumptionFoodItems.entrySet()) {
            var itemData = foodData.get(sampleItem.getKey());
            carbonConsumpton += (sampleItem.getValue() * itemData.getServings() * itemData.getCarbonServing());
            waterConsumption += (sampleItem.getValue() * itemData.getServings() * itemData.getWaterServing());
        }
        return new Consumption(carbonConsumpton, waterConsumption, consumptionFoodItems.size());
    }

    private int getTotalCategoryServings(Map<Long, Item> foodData, Map<Long, Integer> consumptionData, Long category_id) {
        int total = 0;
        for (var consumptionItem : consumptionData.entrySet()) {
            if (foodData.get(consumptionItem.getKey()).getCategory().getId() == category_id)
                total += consumptionItem.getValue() * foodData.get(consumptionItem.getKey()).getServings();
        }
        return total;
    }
}

