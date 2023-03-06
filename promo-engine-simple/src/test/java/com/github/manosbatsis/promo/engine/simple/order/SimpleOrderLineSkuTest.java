package com.github.manosbatsis.promo.engine.simple.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.manosbatsis.promo.engine.simple.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class SimpleOrderLineSkuTest {
  private Product fooProduct = new Product("foo", BigDecimal.ONE);

  @Test
  void Add_PositiveUnits_UpdatesNumber() {
    SimpleOrderLineSku item = new SimpleOrderLineSku(fooProduct, 1);
    item.addUnits(9);
    assertEquals(item.getUnits(), 10);
  }

  @Test
  void Remove_PositiveUnits_UpdatesNumber() {
    SimpleOrderLineSku item = new SimpleOrderLineSku(fooProduct, 10);
    item.removeUnits(9);
    assertEquals(item.getUnits(), 1);
  }

  @Test
  void Add_NegativeUnits_ThrowsError() {
    SimpleOrderLineSku item = new SimpleOrderLineSku(fooProduct, 1);
    AssertionError e =
        assertThrows(
            AssertionError.class,
            () -> {
              item.addUnits(-10);
            });
    assertEquals(e.getMessage(), "Units must be a positive number");
  }
}
