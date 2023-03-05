package com.github.manosbatsis.promo.engine.test;

import static com.github.manosbatsis.promo.engine.test.OrderLineAssertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.manosbatsis.promo.engine.api.Order;
import com.github.manosbatsis.promo.engine.api.OrderLine;
import java.util.List;
import lombok.NonNull;

public class OrderAssertions {

  public static void assertOrderLinesMatch(@NonNull List<ExpectOrderLine> expected, Order actual) {
    assertNotNull(actual, "Expected an order object but got null");
    assert expected.size() == actual.getLines().size()
        : "Expected an order with size " + expected.size() + " was " + actual.getLines().size();
    for (int idx = 0; idx < expected.size(); idx++) {
      ExpectOrderLine expectedLine = expected.get(idx);
      OrderLine actualLine = actual.getLines().get(idx);
      assertOrderLineMatch(expectedLine, actualLine);
    }
  }
}
