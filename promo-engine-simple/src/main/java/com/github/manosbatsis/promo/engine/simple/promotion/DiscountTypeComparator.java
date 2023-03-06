package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.DiscountType;
import java.util.Comparator;

public final class DiscountTypeComparator implements Comparator<DiscountType> {

  public static DiscountTypeComparator instance = new DiscountTypeComparator();

  @Override
  public int compare(DiscountType o1, DiscountType o2) {
    return o1.getPriority().compareTo(o2.getPriority());
  }
}
