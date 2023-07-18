package com.ite.itea.retail.domain.entity.retail;

import java.util.UUID;

public record ProductId(String internalID) {

    public static ProductId random() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
