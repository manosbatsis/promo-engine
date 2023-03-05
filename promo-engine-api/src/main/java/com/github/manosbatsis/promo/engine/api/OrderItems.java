package com.github.manosbatsis.promo.engine.api;

import java.util.SortedMap;

public interface OrderItems {
    public SortedMap<String, OrderItem> getItems();
    public Integer getUnits(Sku sku);

    public Integer addUnits(Sku sku, Integer units);
    public Integer removeUnits(Sku sku, Integer units);
}
