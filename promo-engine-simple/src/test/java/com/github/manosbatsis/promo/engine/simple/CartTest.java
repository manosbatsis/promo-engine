package com.github.manosbatsis.promo.engine.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class CartTest {
  private Product fooProduct = new Product("foo", BigDecimal.ONE);

  @Test
  void Add_PositiveUnits_UpdatesNumber() {
    Cart cart = new Cart().withAddedUnits(fooProduct, 1);
    cart.addUnits(fooProduct, 9);
    assertEquals(cart.getUnitsBySku(fooProduct), 10);
  }

  @Test
  void Remove_PositiveUnits_UpdatesNumber() {
    Cart cart = new Cart().withAddedUnits(fooProduct, 10);
    cart.removeUnits(fooProduct, 9);
    assertEquals(cart.getUnitsBySku(fooProduct), 1);
  }

  @Test
  void Add_NegativeUnits_ThrowsError() {
    Cart cart = new Cart().withAddedUnits(fooProduct, 1);
    AssertionError e =
        assertThrows(
            AssertionError.class,
            () -> {
              cart.addUnits(fooProduct, -10);
            });
    assertEquals(e.getMessage(), "Units must be a positive number");
  }
}
