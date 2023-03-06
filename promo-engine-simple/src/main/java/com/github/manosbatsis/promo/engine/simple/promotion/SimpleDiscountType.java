package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.DiscountType;
import lombok.ToString;

@ToString(includeFieldNames = false)
public enum SimpleDiscountType implements DiscountType {
  FIXED_PRICE(10, "Fixed price"),
  TOTAL_FIXED_PRICE(20, "Total fixed price"),
  FREE(30, "Free"),
  PREORDER_PRICE(40, "Pre-order price"),
  AMOUNT_OFF(50, "Amount-off"),
  PERCENT_OFF(60, "Percent-off"),
  BONUS_PRODUCT(70, "Bonus-product"),
  CHOICE_OF_BONUS_PRODUCTS(80, "Choice of bonus products"),
  FREE_PRODUCT_SHIPPING(90, "Free product-shipping"),
  FIXED_PRICE_PRODUCT_SHIPPING(100, "Fixed price product-shipping");

  public final Integer priority;
  public final String description;

  private SimpleDiscountType(Integer priority, String description) {
    this.priority = priority;
    this.description = description;
  }

  @Override
  public Integer getPriority() {
    return priority;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
