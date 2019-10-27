package com.hackthon.m100u.CarbonMarketAPI.model.entity;

import com.hackthon.m100u.CarbonMarketAPI.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String externalId;
    private String name;
    private Double carbonServing;
    private Double waterServing;
    private Integer servings;
    private Integer servingDay;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_category_id")
    private ItemCategoryEntity category;

    public ItemEntity(long id) {
        this.id = id;
    }

    public Item toItem() {
        Item item = new Item();
        item.setCarbonServing(carbonServing);
        item.setWaterServing(waterServing);
        item.setCategory(category.toCategory());
        item.setCreatedAt(createdAt);
        item.setExternalId(externalId);
        item.setId(id);
        item.setName(name);
        item.setServingDay(servingDay);
        item.setServings(servings);
        return item;
    }
}
