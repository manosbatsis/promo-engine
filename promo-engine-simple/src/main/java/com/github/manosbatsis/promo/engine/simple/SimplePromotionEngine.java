package com.github.manosbatsis.promo.engine.simple;

import com.github.manosbatsis.promo.engine.api.*;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrder;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLine;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLineSku;
import com.github.manosbatsis.promo.engine.simple.promotion.DiscountTypeComparator;
import com.github.manosbatsis.promo.engine.simple.promotion.PromotionPriorityComparator;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * A simple, stateless {@link PromotionEngine} implementation.
 */
@Slf4j
@RequiredArgsConstructor
public class SimplePromotionEngine implements PromotionEngine<Cart, SimpleOrder> {

  @Override
  public SimpleOrder applyPromotions(Set<Promotion> promotions, Cart items) {

    // Sort promotions
    List<Promotion> sortedPromotions = sort(promotions);
    // Treat input items as immutable, use an intermediate copy
    Cart context = items.copy();
    // Process promotions
    SimpleOrder order = new SimpleOrder();
    for (Promotion promotion : sortedPromotions) {
      promotion.apply(context, order);
    }
    // Apply pending items to order
    applyPending(context, order);
    // Return order
    return order;
  }

  /** Sort promotions based on priority, discount type and id */
  private List<Promotion> sort(Set<Promotion> promotions) {
    Comparator<Promotion> promotionComparator =
        Comparator.comparing(Promotion::getPromotionPriority, PromotionPriorityComparator.instance)
            .thenComparing(Promotion::getDiscountType, DiscountTypeComparator.instance)
            .thenComparing(Promotion::getPromotionId);

    return promotions.stream().sorted(promotionComparator).collect(Collectors.toList());
  }

  private void applyPending(Cart applyTo, Order addTo) {
    for (OrderLineSku inputLine : applyTo.getItems()) {
      Integer targetUnits = inputLine.getUnits();
      if (targetUnits == 0) continue;
      Sku targetSku = inputLine.getSku();

      // Create order line entry
      OrderLine line =
          new SimpleOrderLine(
              targetSku.getUnitPrice().multiply(BigDecimal.valueOf(targetUnits)),
              Arrays.asList(new SimpleOrderLineSku(targetSku, targetUnits)),
              Collections.emptySet());
      addTo.addLine(line);

      // Mark units as used
      applyTo.removeUnits(targetSku, targetUnits);
    }
  }
}
