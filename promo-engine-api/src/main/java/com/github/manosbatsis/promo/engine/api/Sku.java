package com.github.manosbatsis.promo.engine.api;

import java.math.BigDecimal;

public interface Sku extends Comparable<Sku> {
  public String getId();

  public BigDecimal getUnitPrice();
}
