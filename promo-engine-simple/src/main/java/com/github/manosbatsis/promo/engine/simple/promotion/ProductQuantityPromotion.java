package com.github.manosbatsis.promo.engine.simple.promotion;

import com.github.manosbatsis.promo.engine.api.DiscountType;
import com.github.manosbatsis.promo.engine.api.Promotion;
import com.github.manosbatsis.promo.engine.api.PromotionPriority;
import lombok.Getter;

@Getter
public class ProductQuantityPromotion implements Promotion {

  private final String promotionId;
  private final String skuId;
  private final Integer quantity;
  private final PromotionPriority promotionPriority =
      SimplePromotionPriority.CLASS_EXCLUSIVE_PRODUCT;
  private final DiscountType discountType = SimpleDiscountType.FIXED_PRICE;

  public ProductQuantityPromotion(String promotionId, String skuId, Integer quantity) {
    this.promotionId = promotionId;
    this.skuId = skuId;
    this.quantity = quantity;
  }
}
