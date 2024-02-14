package com.ite.itea.ecommerce.adapters.out;

import com.ite.itea.ecommerce.adapters.out.presenter.ReceiptPresenter;
import com.ite.itea.ecommerce.domain.core.EuroPrice;
import com.ite.itea.ecommerce.domain.retail.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReceiptPresenterTest {

    private final ReceiptPresenter receiptPresenter = new ReceiptPresenter();

    final Order order01 = Order.of();
    final Order order02 = Order.of(
            new Order.OrderItem(
                    new Picture(ProductId.random(), "Picture \"Norway\"", EuroPrice.ofCents(999)),
                    2
            ));
    final Order order03 = Order.of(
            new Order.OrderItem(
                    new Chair(ProductId.random(), "Chair \"Olaf\"", EuroPrice.ofCents(500), EuroPrice.ofCents(500), EuroPrice.ofCents(500)),
                    2
            )
    );
    final Order order04 = Order.of(
            new Order.OrderItem(
                    new Table(ProductId.random(), "Table \"Lola\"", EuroPrice.ofCents(2000), EuroPrice.ofCents(5000)),
                    2
            )
    );
    final Order order05 = Order.of(
            new Order.OrderItem(
                    new Wardrobe(ProductId.random(), "Wardrobe \"Ingeborg\"", EuroPrice.ofEurosAndCents(249, 99)),
                    2
            )
    );
    final Order order06 = Order.of(
            new Order.OrderItem(
                    new Closet(ProductId.random(), "A closet", EuroPrice.ofEurosAndCents(127, 69)),
                    3
            )
    );

    @Test
    void shouldReturnCorrectReceipts() {
        var receipt = receiptPresenter.prepareReceipt(order01);

        assertThat(receipt.priceInCents()).isEqualTo('?');
        assertThat(receipt.text()).isEqualTo('?');
    }
}
