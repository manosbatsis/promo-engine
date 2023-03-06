package com.github.manosbatsis.promo.engine.api;

import java.util.List;

public interface OrderLine extends Discounted, Comparable<OrderLine> {
  public List<OrderLineSku> getOrderLineSkus();

  public Integer getUnitsForSku(String skuId);
}
