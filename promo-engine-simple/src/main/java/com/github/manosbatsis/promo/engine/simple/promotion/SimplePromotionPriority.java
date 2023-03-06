package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.PromotionPriority;
import lombok.ToString;

@ToString(includeFieldNames = false)
public enum SimplePromotionPriority implements PromotionPriority {
  GLOBAL_EXCLUSIVE(10, "Global Exclusive"),
  CLASS_EXCLUSIVE_PRODUCT(20, "Class Exclusive Product"),
  NON_EXCLUSIVE_PRODUCT(30, "Non-Exclusive Product"),
  CLASS_EXCLUSIVE_ORDER(40, "Class Exclusive Order"),
  NON_EXCLUSIVE_ORDER(50, "Non-Exclusive Order");

  public final Integer priority;
  public final String description;

  private SimplePromotionPriority(Integer priority, String description) {
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
