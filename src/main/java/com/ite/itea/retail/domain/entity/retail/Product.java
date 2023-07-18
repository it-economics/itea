package com.ite.itea.retail.domain.entity.retail;

import com.ite.itea.retail.domain.entity.core.EuroPrice;

public abstract class Product {

    private final ProductId id;
    private final String name;

    public Product(ProductId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        // Empty by default, but subclasses can override.
        return "";
    }

    public abstract EuroPrice price();
}
