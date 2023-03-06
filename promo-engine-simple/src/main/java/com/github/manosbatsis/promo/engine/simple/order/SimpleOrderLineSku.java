package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.OrderLineSku;
import com.github.manosbatsis.promo.engine.api.Sku;
import lombok.*;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class SimpleOrderLineSku implements OrderLineSku {

  @NonNull private final Sku sku;
  @NonNull private Integer units = 0;

  @Override
  public Integer addUnits(Integer units) {
    assert units >= 0 : "Units must be a positive number";
    return this.units = this.units + units;
  }

  @Override
  public Integer removeUnits(Integer units) {
    assert this.units >= units
        : "Cannot remove more units ("
            + units
            + ") than the current number("
            + this.units
            + ") for SKU "
            + sku.getId();
    return this.units = this.units - units;
  }

  @Override
  public int compareTo(OrderLineSku o) {
    return this.sku.compareTo(o.getSku());
  }
}
