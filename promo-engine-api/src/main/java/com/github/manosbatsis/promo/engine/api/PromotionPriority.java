package com.github.manosbatsis.promo.engine.api;

public enum PromotionPriority {
    GLOBAL_EXCLUSIVE(10, "Global Exclusive"),
    CLASS_EXCLUSIVE_PRODUCT(20, "Class Exclusive Product"),
    NON_EXCLUSIVE_PRODUCT(30, "Non-Exclusive Product"),
    CLASS_EXCLUSIVE_ORDER(40, "Class Exclusive Order"),
    NON_EXCLUSIVE_ORDER(50, "Non-Exclusive Order");

    public final Integer priority;
    public final String description;

    private PromotionPriority(Integer priority, String description) {
        this.priority = priority;
        this.description = description;
    }
}
