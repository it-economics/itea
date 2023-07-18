package com.ite.itea.retail.domain.entity.retail;

import com.ite.itea.retail.domain.entity.core.EuroPrice;

public final class Table extends Product {

    private final EuroPrice legPrice;
    private final EuroPrice tableTopPrice;

    public Table(ProductId id, String name, EuroPrice legPrice, EuroPrice tableTopPrice) {
        super(id, name);
        this.legPrice = legPrice;
        this.tableTopPrice = tableTopPrice;
    }

    @Override
    public EuroPrice price() {
        return legPrice.times(4).plus(tableTopPrice);
    }
}
