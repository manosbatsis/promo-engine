package com.github.manosbatsis.promo.engine.simple;

import com.github.manosbatsis.promo.engine.api.Sku;
import java.math.BigDecimal;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Product implements Sku {

  private String id;
  private BigDecimal unitPrice;

  @Override
  public int compareTo(Sku other) {
    return this.id.compareTo(other.getId());
  }
}
