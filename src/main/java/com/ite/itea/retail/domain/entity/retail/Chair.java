package com.ite.itea.retail.domain.entity.retail;

import com.ite.itea.retail.domain.entity.core.EuroPrice;

public final class Chair extends Product {

    private final EuroPrice legPrice;
    private final EuroPrice seatPrice;
    private final EuroPrice backRestPrice;

    public Chair(ProductId id, String name, EuroPrice legPrice, EuroPrice seatPrice, EuroPrice backRestPrice) {
        super(id, name);
        this.legPrice = legPrice;
        this.seatPrice = seatPrice;
        this.backRestPrice = backRestPrice;
    }

    @Override
    public EuroPrice price() {
        return legPrice.times(4).plus(seatPrice).plus(backRestPrice);
    }
}
