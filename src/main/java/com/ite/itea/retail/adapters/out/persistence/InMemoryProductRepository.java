package com.ite.itea.retail.adapters.out.persistence;

import com.ite.itea.retail.domain.entity.core.EuroPrice;
import com.ite.itea.retail.domain.entity.retail.*;
import com.ite.itea.retail.domain.usecase.ProductRepository;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> fakeInMemoryDatabase =
            Map.ofEntries(
                    entry(
                            ProductName.PICTURE_FINLAND.name(),
                            new Picture(
                                    new ProductId(ProductName.PICTURE_FINLAND.name()),
                                    ProductName.PICTURE_FINLAND.displayName(),
                                    EuroPrice.ofCents(1499)
                            )
                    ),
                    entry(
                            ProductName.PICTURE_NORWAY.name(),
                            new Picture(
                                    new ProductId(ProductName.PICTURE_NORWAY.name()),
                                    ProductName.PICTURE_NORWAY.displayName(),
                                    EuroPrice.ofCents(999)
                            )
                    ),
                    entry(
                            ProductName.PICTURE_SWEDEN.name(),
                            new Picture(
                                    new ProductId(ProductName.PICTURE_SWEDEN.name()),
                                    ProductName.PICTURE_SWEDEN.displayName(),
                                    EuroPrice.ofCents(1299)
                            )
                    ),
                    entry(
                            ProductName.TABLE_LOLA.name(),
                            new Table(
                                    new ProductId(ProductName.TABLE_LOLA.name()),
                                    ProductName.TABLE_LOLA.displayName(),
                                    EuroPrice.ofCents(3000),
                                    EuroPrice.ofCents(1000)
                            )
                    ),
                    entry(
                            ProductName.TABLE_LOTTA.name(),
                            new Table(
                                    new ProductId(ProductName.TABLE_LOTTA.name()),
                                    ProductName.TABLE_LOTTA.displayName(),
                                    EuroPrice.ofCents(1000),
                                    EuroPrice.ofCents(1000)
                            )
                    ),
                    entry(
                            ProductName.CHAIR_ELSA.name(),
                            new Chair(
                                    new ProductId(ProductName.CHAIR_ELSA.name()),
                                    ProductName.CHAIR_ELSA.displayName(),
                                    EuroPrice.ofCents(500),
                                    EuroPrice.ofCents(500),
                                    EuroPrice.ofCents(500)
                            )
                    ),
                    entry(
                            ProductName.CHAIR_KNUT.name(),
                            new Chair(
                                    new ProductId(ProductName.CHAIR_KNUT.name()),
                                    ProductName.CHAIR_KNUT.displayName(),
                                    EuroPrice.ofCents(400),
                                    EuroPrice.ofCents(1000),
                                    EuroPrice.ofCents(1500)
                            )
                    ),
                    entry(
                            ProductName.CHAIR_LARS.name(),
                            new Chair(
                                    new ProductId(ProductName.CHAIR_LARS.name()),
                                    ProductName.CHAIR_LARS.displayName(),
                                    EuroPrice.ofCents(200),
                                    EuroPrice.ofCents(2000),
                                    EuroPrice.ofCents(3000)
                            )
                    )
            );

    @Override
    public Optional<Product> byId(ProductId id) {
        return Optional.ofNullable(fakeInMemoryDatabase.getOrDefault(id.internalID(), null));
    }
}
