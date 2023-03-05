package com.github.manosbatsis.promo.engine.api;

import java.math.BigDecimal;
import java.util.Set;

public interface Discounted {
  public Set<String> getAppliedPromotions();

  public BigDecimal getDiscountedPrice();
}
