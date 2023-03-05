package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.DiscountType;
import com.github.manosbatsis.promo.engine.api.Promotion;
import com.github.manosbatsis.promo.engine.api.PromotionPriority;
import java.util.Set;
import lombok.Getter;

@Getter
public class ProductsBundlePromotion implements Promotion {

  private final String promotionId;
  private final Set<String> skus;
  private final PromotionPriority promotionPriority =
      SimplePromotionPriority.CLASS_EXCLUSIVE_PRODUCT;
  private final DiscountType discountType = SimpleDiscountType.FIXED_PRICE;

  public ProductsBundlePromotion(String promotionId, Set<String> skus) {
    this.promotionId = promotionId;
    this.skus = skus;
  }
}
