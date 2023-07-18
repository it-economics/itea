package com.ite.itea.retail.domain.usecase;

import com.ite.itea.retail.domain.entity.retail.Product;
import com.ite.itea.retail.domain.entity.retail.ProductId;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> byId(ProductId id);
}
