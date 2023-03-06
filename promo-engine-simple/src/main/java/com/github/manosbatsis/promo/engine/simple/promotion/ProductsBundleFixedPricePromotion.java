package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.*;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLine;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLineSku;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class ProductsBundleFixedPricePromotion implements Promotion {

  private final String promotionId;
  private final Set<String> skuIds;
  private final BigDecimal price;

  private final PromotionPriority promotionPriority =
      SimplePromotionPriority.CLASS_EXCLUSIVE_PRODUCT;
  private final DiscountType discountType = SimpleDiscountType.FIXED_PRICE;

  public ProductsBundleFixedPricePromotion(String promotionId, Set<String> skus, BigDecimal price) {
    this.promotionId = promotionId;
    this.skuIds = skus;
    this.price = price;
  }

  @Override
  public void apply(OrderLineSkus applyTo, Order addTo) {
    Set<Sku> targetSkus =
        skuIds.stream()
            .map(it -> applyTo.findSku(it))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

    // NOOP
    if (skuIds.size() != targetSkus.size()) return;

    // Create order line entry
    OrderLine line =
        new SimpleOrderLine(
            price,
            targetSkus.stream()
                .map(it -> new SimpleOrderLineSku(it, 1))
                .collect(Collectors.toList()),
            Collections.singleton(this));
    addTo.addLine(line);

    // Mark units as used
    for (Sku sku : targetSkus) applyTo.removeUnits(sku, 1);
  }
}
