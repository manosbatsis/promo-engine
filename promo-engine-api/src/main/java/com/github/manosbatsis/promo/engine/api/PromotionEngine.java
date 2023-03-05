package com.github.manosbatsis.promo.engine.api;

public interface PromotionEngine<IN extends OrderItems, OUT extends Order> {
    public OUT applyPromotions(IN items);
}
