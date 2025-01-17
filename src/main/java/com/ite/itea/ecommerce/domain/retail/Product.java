package com.ite.itea.ecommerce.domain.retail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ite.itea.ecommerce.domain.core.EuroPrice;

public abstract class Product {

    @JsonProperty
    private final ProductId id;
    @JsonProperty
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

    @JsonProperty
    public abstract EuroPrice price();

    @Override
    public String toString() {
        return "Product { %s }".formatted(name);
    }
}
