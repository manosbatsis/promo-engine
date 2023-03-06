package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.PromotionPriority;
import java.util.Comparator;

public final class PromotionPriorityComparator implements Comparator<PromotionPriority> {

  public static PromotionPriorityComparator instance = new PromotionPriorityComparator();

  @Override
  public int compare(PromotionPriority o1, PromotionPriority o2) {
    return o1.getPriority().compareTo(o2.getPriority());
  }
}
