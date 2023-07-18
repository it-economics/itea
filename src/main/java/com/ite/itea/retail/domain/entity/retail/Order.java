package com.ite.itea.retail.domain.entity.retail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record Order(List<OrderItem> items) {

    public static Order of(OrderItem... items) {
        return new Order(Arrays.asList(items));
    }

    public List<OrderItem> items() {
        return Collections.unmodifiableList(items);
    }

    public record OrderItem(Product product, int amount) {
    }
}
