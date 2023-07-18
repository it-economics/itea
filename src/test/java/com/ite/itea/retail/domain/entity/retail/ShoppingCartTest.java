package com.ite.itea.retail.domain.entity.retail;

import com.ite.itea.retail.domain.entity.core.EuroPrice;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {

    @Test
    void emptyCart_hasPriceOfZeroCents() {
        final var emptyCart = ShoppingCart.empty();

        assertThat(emptyCart.price()).isEqualTo(EuroPrice.ofCents(0));
    }

    @Test
    void cartWithMultipleItems_costsTheSumOfTheItemsItContains() {
        final var cart = new ShoppingCart();
        final var beautifulPicture = new Picture(ProductId.random(), "A beautiful picture", EuroPrice.ofCents(1337));
        final var uglyPicture = new Picture(ProductId.random(), "An ugly picture", EuroPrice.ofCents(42));

        cart.add(beautifulPicture);
        cart.add(uglyPicture);

        assertThat(cart.price()).isEqualTo(EuroPrice.ofCents(1379));
    }
}
