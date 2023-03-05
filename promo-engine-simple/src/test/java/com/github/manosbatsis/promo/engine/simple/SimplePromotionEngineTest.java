package com.github.manosbatsis.promo.engine.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.manosbatsis.promo.engine.api.Order;
import com.github.manosbatsis.promo.engine.api.PromotionEngine;
import com.github.manosbatsis.promo.engine.simple.promotion.ProductQuantityFixedPricePromotion;
import com.github.manosbatsis.promo.engine.simple.promotion.ProductsBundleFixedPricePromotion;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

public class SimplePromotionEngineTest {

  // Test Setup: Products
  Product productA = new Product("A", BigDecimal.valueOf(50));
  Product productB = new Product("B", BigDecimal.valueOf(30));
  Product productC = new Product("C", BigDecimal.valueOf(20));
  Product productD = new Product("D", BigDecimal.valueOf(15));

  // Test Setup: Active Promotions
  ProductQuantityFixedPricePromotion setOfThreeAsPromo =
      new ProductQuantityFixedPricePromotion(
          "3 of A's for 130", productA.getId(), 3, BigDecimal.valueOf(130));
  ProductQuantityFixedPricePromotion setOfTwoBsPromo =
      new ProductQuantityFixedPricePromotion(
          "2 of B's for 45", productA.getId(), 2, BigDecimal.valueOf(45));
  ProductsBundleFixedPricePromotion cAndDPromo =
      new ProductsBundleFixedPricePromotion(
          "C & D for 30",
          new HashSet<>(Arrays.asList(productC.getId(), productD.getId())),
          BigDecimal.valueOf(30));

  // Test Setup: Promotions Engine
  PromotionEngine engine =
      new SimplePromotionEngine(setOfThreeAsPromo, setOfTwoBsPromo, cAndDPromo);

  /** Scenario A: 1 * A 50, 1 * B 30, 1 * C 20, Total 100 */
  @Test
  void Scenario_A() {
    Cart cart =
        new Cart()
            .withAddedUnits(productA, 1)
            .withAddedUnits(productB, 1)
            .withAddedUnits(productC, 1);
    Order order = engine.applyPromotions(cart);
    assertEquals(100, order.getDiscountedPrice());
  }

  /** Scenario B: 5 * A 130 + 2*50, 5 * B 45 + 45 + 30, 1 * C 28 Total 370 */
  @Test
  void Scenario_B() {
    Cart cart =
        new Cart()
            .withAddedUnits(productA, 5)
            .withAddedUnits(productB, 5)
            .withAddedUnits(productC, 1);
    Order order = engine.applyPromotions(cart);
    assertEquals(370, order.getDiscountedPrice());
  }

  /** Scenario C: 3 * A 130, 5 * B 45 + 45 + 1 * 30, 1 * C & 1 * D 30 Total 280 */
  @Test
  void Scenario_C() {
    Cart cart =
        new Cart()
            .withAddedUnits(productA, 3)
            .withAddedUnits(productB, 5)
            .withAddedUnits(productC, 1)
            .withAddedUnits(productD, 1);
    Order order = engine.applyPromotions(cart);
    assertEquals(280, order.getDiscountedPrice());
  }
}
