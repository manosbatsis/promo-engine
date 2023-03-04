package com.github.manosbatsis.promo.engine.api;

public interface Promotion {
    public String getPromotionId();
    public PromotionPriority getPromotionPriority();
    public DiscountType getDiscountType();
}
