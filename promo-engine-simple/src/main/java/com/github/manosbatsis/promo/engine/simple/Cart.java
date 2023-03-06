package com.github.manosbatsis.promo.engine.simple;

import com.github.manosbatsis.promo.engine.api.OrderLineSku;
import com.github.manosbatsis.promo.engine.api.OrderLineSkus;
import com.github.manosbatsis.promo.engine.api.Sku;
import com.github.manosbatsis.promo.engine.simple.order.SimpleOrderLineSku;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Cart implements OrderLineSkus {

  final Map<String, OrderLineSku> itemsMap = new HashMap<>();

  @Override
  public SortedMap<String, Integer> toMap() {
    return itemsMap.values().stream()
        .collect(
            Collectors.toMap(
                e -> e.getSku().getId(), e -> e.getUnits(), Math::addExact, TreeMap::new));
  }

  @Override
  public SortedSet<OrderLineSku> getItems() {
    SortedSet<OrderLineSku> items = new TreeSet<>();
    items.addAll(itemsMap.values());
    return items;
  }

  @Override
  public Integer getUnitsBySku(String skuId) {
    OrderLineSku match = itemsMap.get(skuId);
    return match != null ? match.getUnits() : 0;
  }

  @Override
  public Sku findSku(String skuId) {
    return itemsMap.containsKey(skuId) ? itemsMap.get(skuId).getSku() : null;
  }

  @Override
  public Integer getUnitsBySku(Sku sku) {
    return getUnitsBySku(sku.getId());
  }

  @Override
  public Integer addUnits(Sku sku, Integer units) {
    assert units > 0 : "Units must be a positive number";
    Integer itemUnits = units;
    if (itemsMap.containsKey(sku.getId())) itemUnits = itemsMap.get(sku.getId()).addUnits(units);
    else itemsMap.put(sku.getId(), new SimpleOrderLineSku(sku, units));
    return itemUnits;
  }

  @Override
  public Integer removeUnits(Sku sku, Integer units) {
    assert itemsMap.containsKey(sku.getId())
        : "Cannot remove units from missing SKU: " + sku.getId();
    OrderLineSku item = itemsMap.get(sku.getId());
    assert item.getUnits() >= units
        : "Cannot remove more units ("
            + units
            + ") than the current number ("
            + item.getUnits()
            + ") for SKU "
            + sku.getId();
    return item.removeUnits(units);
  }

  public Cart withAddedUnits(Sku sku, Integer units) {
    addUnits(sku, units);
    return this;
  }

  public Cart withRemovedUnits(Sku sku, Integer units) {
    removeUnits(sku, units);
    return this;
  }

  public Cart copy() {
    Cart cart = new Cart();
    SortedSet<OrderLineSku> items = this.getItems();
    for (OrderLineSku item : items) cart.addUnits(item.getSku(), item.getUnits());
    return cart;
  }
}
