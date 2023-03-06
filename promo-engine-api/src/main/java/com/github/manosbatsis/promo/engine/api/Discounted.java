package com.github.manosbatsis.promo.engine.api;

import java.math.BigDecimal;
import java.util.Set;

public interface Discounted {

  /** Retrieve the IDs of all [Promotion] applied at this level */
  public Set<Promotion> getAppliedPromotions();

  /** Retrieve the discounted price for this level */
  public BigDecimal getDiscountedPrice();
}
