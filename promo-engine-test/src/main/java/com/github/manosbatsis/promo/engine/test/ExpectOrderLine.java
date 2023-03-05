package com.github.manosbatsis.promo.engine.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpectOrderLine {
  private BigDecimal discountedPrice;
  private List<ExpectSkuItems> lineSkuItems;

  public static ExpectOrderLine of(Integer discountedPrice, ExpectSkuItems... skuIdAndUnits) {
    List<ExpectSkuItems> skuIdAndUnitsSet = Arrays.asList(skuIdAndUnits);
    assert skuIdAndUnitsSet.size() == skuIdAndUnits.length
        : "Input skuIdAndUnits contains duplicate SKU ID(s)";
    return new ExpectOrderLine(BigDecimal.valueOf(discountedPrice), skuIdAndUnitsSet);
  }
}
