package com.github.manosbatsis.promo.engine.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"skuId"})
public class ExpectSkuItems {

  private String skuId;
  private Integer units;

  public static ExpectSkuItems of(@NonNull String skuId, @NonNull Integer units) {
    assert units > 0 : "Expected positive, non-zero units but was " + units;
    return new ExpectSkuItems(skuId, units);
  }
}
