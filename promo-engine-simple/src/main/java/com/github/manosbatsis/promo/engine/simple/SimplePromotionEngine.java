package com.github.manosbatsis.promo.engine.simple;

import com.github.manosbatsis.promo.engine.api.Promotion;
import com.github.manosbatsis.promo.engine.api.PromotionEngine;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimplePromotionEngine implements PromotionEngine<Cart, SimpleOrder> {

  private final Set<Promotion> promotions;

  public SimplePromotionEngine(Promotion... promotions) {
    this(new HashSet<>(Arrays.asList(promotions)));
  }

  @Override
  public SimpleOrder applyPromotions(Cart items) {
    return null;
  }
}
