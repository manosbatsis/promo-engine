package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.OrderLine;
import com.github.manosbatsis.promo.engine.api.OrderLineSku;
import com.github.manosbatsis.promo.engine.api.Promotion;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class SimpleOrderLine implements OrderLine {

  @NonNull private final BigDecimal discountedPrice;
  @NonNull private final List<OrderLineSku> orderLineSkus;
  @NonNull private final Set<Promotion> appliedPromotions;

  @Override
  public Integer getUnitsForSku(@NonNull String skuId) {
    for (OrderLineSku item : getOrderLineSkus()) {
      if (skuId == item.getSku().getId()) return item.getUnits();
    }
    return null;
  }

  @Override
  public int compareTo(OrderLine o) {
    List<OrderLineSku> o1 = this.orderLineSkus;
    List<OrderLineSku> o2 = o.getOrderLineSkus();
    for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
      int c = o1.get(i).compareTo(o2.get(i));
      if (c != 0) {
        return c;
      }
    }
    return Integer.compare(o1.size(), o2.size());
  }
}
