package com.github.manosbatsis.promo.engine.simple.order;

import com.github.manosbatsis.promo.engine.api.OrderItem;
import com.github.manosbatsis.promo.engine.api.Sku;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class SimpleOrderItem implements OrderItem {

    private final Sku sku;
    private Integer units;

    @Override
    public Integer addUnits(Integer units) {
        assert units > 0 : "Units must be a positive number";
        return this.units = this.units + units;
    }

    @Override
    public Integer removeUnits(Integer units) {
        assert this.units > units : "Cannot remove more units (" + units +
                ") than the current number(" + this.units + ") for SKU " + sku.getId();
        return this.units = this.units - units;
    }
}
