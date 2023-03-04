package com.github.manosbatsis.promo.engine.api;

import java.math.BigDecimal;
import java.util.List;

public interface OrderLine extends Discounted {
    public List<OrderItem> getOrderItems();
}
