package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.OrderItem;
import com.github.manosbatsis.promo.engine.api.OrderLine;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SimpleOrderLine implements OrderLine {
  @Override
  public Set<String> getAppliedPromotions() {
    return null;
  }

  @Override
  public BigDecimal getDiscountedPrice() {
    return null;
  }

  @Override
  public List<OrderItem> getOrderItems() {
    return null;
  }
}
