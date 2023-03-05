package com.github.manosbatsis.promo.engine.api;

import java.math.BigDecimal;

public interface Order extends OrderLines, Discounted {
  public BigDecimal getDiscountedTotalPrice();
}
