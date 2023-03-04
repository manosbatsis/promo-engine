package com.github.manosbatsis.promo.engine.api;

import java.util.Map;

public interface OrderItems {
    public Map<String, OrderItem> getItems();
    public Integer addItems(Sku sku, Integer items);
    public Integer removeItems(Sku sku, Integer items);
}
