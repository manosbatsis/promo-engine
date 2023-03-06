package com.github.manosbatsis.promo.engine.api;

import java.util.Set;

public interface PromotionEngine<IN extends OrderLineSkus, OUT extends Order> {

  /**
   * Apply the given set of promotions to the input order lines
   *
   * @return the resulting order with promotions applied
   */
  public OUT applyPromotions(Set<Promotion> promotions, IN items);
}
