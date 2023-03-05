package com.github.manosbatsis.promo.engine.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.manosbatsis.promo.engine.api.OrderItem;
import com.github.manosbatsis.promo.engine.api.OrderLine;
import com.github.manosbatsis.promo.engine.api.Sku;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
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

  public static void assertSkuIdSetEquals(@NonNull Collection<String> expected, OrderLine actual) {
    assertNotNull(actual, "Expected an order line with single SKU but got null");
    Set<String> expectedSet =
        expected.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    Set<String> actualSet =
        actual.getOrderItems().stream()
            .map(OrderItem::getSku)
            .map(Sku::getId)
            .sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
    assertEquals(expectedSet, actualSet);
  }
}
