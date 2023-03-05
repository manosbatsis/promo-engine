package com.github.manosbatsis.promo.engine.test;

import static com.github.manosbatsis.promo.engine.test.DiscountedAssertions.assertDiscountedPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.manosbatsis.promo.engine.api.OrderLine;
import java.util.List;
import lombok.NonNull;

public class OrderLineAssertions {
  public static void assertSkuIdEquals(@NonNull String expected, OrderLine actual) {
    assertNotNull(actual, "Expected an order line with single SKU but got null");
    assertEquals(
        1,
        actual.getOrderItems().size(),
        "Expected an order line with single SKU but got multiple");
    assertEquals(expected, actual.getOrderItems().get(0).getSku().getId());
  }

  public static void assertLineSkuItemsMatch(
          @NonNull List<ExpectSkuItems> expected, OrderLine actual) {
    assertNotNull(actual, "Expected an order line but got null");
    for (int idx = 0; idx < expected.size(); idx++) {
      ExpectSkuItems expectedLine = expected.get(idx);
      assertEquals(expectedLine.getUnits(), actual.getSkuUnits(expectedLine.getSkuId()));
    }
  }

  public static void assertOrderLineMatch(@NonNull ExpectOrderLine expected, OrderLine actual) {
    assertNotNull(actual, "Expected an order line but got null");
    assertLineSkuItemsMatch(expected.getLineSkuItems(), actual);
    assertDiscountedPrice(expected.getDiscountedPrice(), actual);
  }
}
