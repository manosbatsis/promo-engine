package com.github.manosbatsis.promo.engine.api;

import java.util.List;

public interface OrderLine extends Discounted {
  public List<OrderItem> getOrderItems();

  public Integer getSkuUnits(String skuId);
}
