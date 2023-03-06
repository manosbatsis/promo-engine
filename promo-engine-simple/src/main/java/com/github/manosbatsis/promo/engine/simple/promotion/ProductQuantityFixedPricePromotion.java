package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.*;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLine;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLineSku;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class ProductQuantityFixedPricePromotion implements Promotion {

  private final String promotionId;
  private final String skuId;
  private final Integer quantity;
  private final BigDecimal price;
  private final PromotionPriority promotionPriority =
      SimplePromotionPriority.CLASS_EXCLUSIVE_PRODUCT;
  private final DiscountType discountType = SimpleDiscountType.FIXED_PRICE;

  public ProductQuantityFixedPricePromotion(
      String promotionId, String skuId, Integer quantity, BigDecimal price) {
    this.promotionId = promotionId;
    this.skuId = skuId;
    this.quantity = quantity;
    this.price = price;
  }

  @Override
  public void apply(OrderLineSkus applyTo, Order addTo) {
    Sku targetSku = applyTo.findSku(skuId);
    Integer timesApplied = targetSku != null ? applyTo.getUnitsBySku(skuId) / quantity : 0;

    // NOOP
    if (timesApplied == 0) return;

    Integer unitCountUsed = timesApplied * quantity;
    // Create order line entry
    OrderLine line =
        new SimpleOrderLine(
            price.multiply(BigDecimal.valueOf(timesApplied)),
            Arrays.asList(new SimpleOrderLineSku(targetSku, unitCountUsed)),
            Collections.singleton(this));
    addTo.addLine(line);

    // Mark units as used
    applyTo.removeUnits(targetSku, unitCountUsed);
  }
}
