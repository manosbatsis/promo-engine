package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.Order;
import com.github.manosbatsis.promo.engine.api.OrderLine;
import com.github.manosbatsis.promo.engine.api.Promotion;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
@ToString
public class SimpleOrder implements Order {
  private final List<OrderLine> lines = new LinkedList<>();

  @Override
  public Set<Promotion> getAppliedPromotions() {
    throw new UnsupportedOperationException("Promotions not implemented at order level");
  }

  @Override
  public List<OrderLine> getLines() {
    return lines.stream().sorted().collect(Collectors.toList());
  }

  @Override
  public void addLine(OrderLine line) {
    lines.add(line);
  }

  @Override
  public BigDecimal getDiscountedPrice() {
    return lines.stream()
        .map(it -> it.getDiscountedPrice())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
