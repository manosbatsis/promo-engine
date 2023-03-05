package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.DiscountType;
import com.github.manosbatsis.promo.engine.api.Promotion;
import com.github.manosbatsis.promo.engine.api.PromotionPriority;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;

@Getter
public class ProductsBundleFixedPricePromotion implements Promotion {

  private final String promotionId;
  private final Set<String> skus;
  private final BigDecimal price;
  private final PromotionPriority promotionPriority =
      SimplePromotionPriority.CLASS_EXCLUSIVE_PRODUCT;
  private final DiscountType discountType = SimpleDiscountType.FIXED_PRICE;

  public ProductsBundleFixedPricePromotion(String promotionId, Set<String> skus, BigDecimal price) {
    this.promotionId = promotionId;
    this.skus = skus;
    this.price = price;
  }
}
