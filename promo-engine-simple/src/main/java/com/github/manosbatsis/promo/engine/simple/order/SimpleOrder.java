package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.Order;
import com.github.manosbatsis.promo.engine.api.OrderLine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleOrder implements Order {

  @Override
  public Set<String> getAppliedPromotions() {
    return null;
  }

  @Override
  public BigDecimal getDiscountedPrice() {
    return BigDecimal.ZERO;
  }

  @Override
  public List<OrderLine> getLines() {
    return new ArrayList<OrderLine>();
  }
}
