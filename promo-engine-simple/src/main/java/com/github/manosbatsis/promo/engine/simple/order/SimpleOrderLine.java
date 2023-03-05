package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.OrderItem;
import com.github.manosbatsis.promo.engine.api.OrderLine;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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

  @Override
  @NonNull
  public Integer getSkuUnits(@NonNull String skuId) {
    for (OrderItem item : getOrderItems()) {
      if (skuId == item.getSku().getId()) return item.getUnits();
    }
    return 0;
  }
}
