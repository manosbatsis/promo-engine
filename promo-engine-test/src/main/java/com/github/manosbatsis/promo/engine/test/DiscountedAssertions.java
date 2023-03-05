package com.github.manosbatsis.promo.engine.test;

import static org.hamcrest.MatcherAssert.assertThat;

import com.github.manosbatsis.promo.engine.api.Discounted;
import java.math.BigDecimal;
import org.hamcrest.Matchers;

public class DiscountedAssertions {
  public static void assertDiscountedPrice(BigDecimal expected, Discounted actual) {
    assertThat(actual.getDiscountedPrice(), Matchers.comparesEqualTo(expected));
  }

  public static void assertDiscountedPrice(Long expected, Discounted actual) {
    assertDiscountedPrice(BigDecimal.valueOf(expected), actual);
  }

  public static void assertDiscountedPrice(Double expected, Discounted actual) {
    assertDiscountedPrice(BigDecimal.valueOf(expected), actual);
  }
}
