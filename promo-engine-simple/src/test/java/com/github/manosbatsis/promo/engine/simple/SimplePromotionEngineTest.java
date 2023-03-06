package com.github.manosbatsis.promo.engine.simple;

import static com.github.manosbatsis.promo.engine.test.DiscountedAssertions.*;
import static com.github.manosbatsis.promo.engine.test.OrderAssertions.*;

import com.github.manosbatsis.promo.engine.api.Order;
import com.github.manosbatsis.promo.engine.api.Promotion;
import com.github.manosbatsis.promo.engine.api.PromotionEngine;
import com.github.manosbatsis.promo.engine.simple.promotion.ProductQuantityFixedPricePromotion;
import com.github.manosbatsis.promo.engine.simple.promotion.ProductsBundleFixedPricePromotion;
import com.github.manosbatsis.promo.engine.test.ExpectOrderLine;
import com.github.manosbatsis.promo.engine.test.ExpectSkuItems;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
          "2 of B's for 45", productB.getId(), 2, BigDecimal.valueOf(45));
  ProductsBundleFixedPricePromotion cAndDPromo =
      new ProductsBundleFixedPricePromotion(
          "C & D for 30",
          new HashSet<>(Arrays.asList(productC.getId(), productD.getId())),
          BigDecimal.valueOf(30));

  // Test Setup: Promotions Engine
  Set<Promotion> promotions =
      new HashSet<>(Arrays.asList(setOfThreeAsPromo, setOfTwoBsPromo, cAndDPromo));
  PromotionEngine engine = new SimplePromotionEngine();

  /** Scenario A: 1 * A 50, 1 * B 30, 1 * C 20, Total 100 */
  @Test
  void Scenario_A() {
    Cart cart =
        new Cart()
            .withAddedUnits(productA, 1)
            .withAddedUnits(productB, 1)
            .withAddedUnits(productC, 1);
    Order order = engine.applyPromotions(promotions, cart);
    // Check line-by-line
    List<ExpectOrderLine> expectedLines =
        Arrays.asList(
            ExpectOrderLine.of(50, ExpectSkuItems.of("A", 1)),
            ExpectOrderLine.of(30, ExpectSkuItems.of("B", 1)),
            ExpectOrderLine.of(20, ExpectSkuItems.of("C", 1)));
    // Check total
    assertDiscountedPrice(100, order);
  }

  /** Scenario B: 5 * A 130 + 2*50, 5 * B 45 + 45 + 30, 1 * C 20 Total 370 */
  @Test
  void Scenario_B() {
    Cart cart =
        new Cart()
            .withAddedUnits(productA, 5)
            .withAddedUnits(productB, 5)
            .withAddedUnits(productC, 1);
    Order order = engine.applyPromotions(promotions, cart);
    // Check line-by-line
    List<ExpectOrderLine> expectedLines =
        Arrays.asList(
            ExpectOrderLine.of(130, ExpectSkuItems.of("A", 3)),
            ExpectOrderLine.of(2 * 50, ExpectSkuItems.of("A", 2)),
            ExpectOrderLine.of(45 + 45, ExpectSkuItems.of("B", 4)),
            ExpectOrderLine.of(30, ExpectSkuItems.of("B", 1)),
            ExpectOrderLine.of(20, ExpectSkuItems.of("C", 1)));
    assertOrderLinesMatch(expectedLines, order);
    // Check total
    assertDiscountedPrice(370, order);
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
    Order order = engine.applyPromotions(promotions, cart);
    // Check line-by-line
    List<ExpectOrderLine> expectedLines =
        Arrays.asList(
            ExpectOrderLine.of(130, ExpectSkuItems.of("A", 3)),
            ExpectOrderLine.of(45 + 45, ExpectSkuItems.of("B", 4)),
            ExpectOrderLine.of(30, ExpectSkuItems.of("B", 1)),
            ExpectOrderLine.of(30, ExpectSkuItems.of("C", 1), ExpectSkuItems.of("D", 1)));
    assertOrderLinesMatch(expectedLines, order);
    // Check total
    assertDiscountedPrice(280, order);
  }
}
