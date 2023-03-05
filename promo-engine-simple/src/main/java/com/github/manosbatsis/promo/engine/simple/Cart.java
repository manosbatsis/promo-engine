package com.github.manosbatsis.promo.engine.simple;

import com.github.manosbatsis.promo.engine.api.OrderItem;
import com.github.manosbatsis.promo.engine.api.OrderItems;
import com.github.manosbatsis.promo.engine.api.Sku;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderItem;
import lombok.Getter;

import java.util.TreeMap;


@Getter
public class Cart implements OrderItems {

    private final TreeMap<String, OrderItem> items = new TreeMap<>();


    public Cart withAddedUnits(Sku sku, Integer units) {
        addUnits(sku, units);
        return this;
    }

    public Cart withRemovedUnits(Sku sku, Integer units) {
        removeUnits(sku, units);
        return this;
    }

    @Override
    public Integer getUnits(Sku sku) {
        return items.get(sku.getId()).getUnits();
    }

    @Override
    public Integer addUnits(Sku sku, Integer units) {
        assert units > 0 : "Units must be a positive number";
        Integer itemUnits = units;
        if (items.containsKey(sku.getId()))
            itemUnits = items.get(sku.getId()).addUnits(units);

        else items.put(sku.getId(), new SimpleOrderItem(sku, units));
        return itemUnits;
    }

    @Override
    public Integer removeUnits(Sku sku, Integer units) {
        assert items.containsKey(sku.getId()) : "Cannot remove units from missing SKU: " + sku.getId();
        OrderItem item = items.get(sku.getId());
        assert item.getUnits() > units : "Cannot remove more units than the current number: " + sku.getId();
        return item.removeUnits(units);
    }
}
