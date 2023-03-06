package com.github.manosbatsis.promo.engine.api;

import java.util.SortedMap;
import java.util.SortedSet;

public interface OrderLineSkus {
  public SortedMap<String, Integer> toMap();

  public SortedSet<OrderLineSku> getItems();

  public Integer getUnitsBySku(Sku sku);

  public Integer getUnitsBySku(String skuId);

  public Sku findSku(String skuId);

  public Integer addUnits(Sku sku, Integer units);

  public Integer removeUnits(Sku sku, Integer units);
}
