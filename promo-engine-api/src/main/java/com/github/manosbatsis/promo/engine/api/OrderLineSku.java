package com.github.manosbatsis.promo.engine.api;

public interface OrderLineSku extends Comparable<OrderLineSku> {
  public Sku getSku();

  public Integer getUnits();

  public Integer addUnits(Integer units);

  public Integer removeUnits(Integer units);
}
