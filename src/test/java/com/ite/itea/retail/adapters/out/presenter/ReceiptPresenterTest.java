package com.ite.itea.retail.adapters.out.presenter;

import com.ite.itea.retail.domain.entity.core.EuroPrice;
import com.ite.itea.retail.domain.entity.retail.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class ReceiptPresenterTest {

    private final ReceiptPresenter receiptPresenter = new ReceiptPresenter();

    private static Stream<Arguments> provideProductsAndExpectedReceipts() {
        return Stream.of(
                Arguments.of(
                        Order.of(),
                        EuroPrice.zero(),
                        "itea \nTotal 0,00\u00A0€"
                ),
                Arguments.of(
                        Order.of(
                                new Order.OrderItem(
                                        new Picture(ProductId.random(), ProductName.PICTURE_NORWAY.displayName(), EuroPrice.ofCents(999)),
                                        2
                                )
                        ),
                        EuroPrice.ofCents(1998L),
                        "itea \nPicture \"Norway\" 9,99\u00A0€ * 2\nTotal 19,98\u00A0€"
                ),
                Arguments.of(
                        Order.of(
                                new Order.OrderItem(
                                        new Chair(ProductId.random(), ProductName.CHAIR_ELSA.displayName(), EuroPrice.ofCents(500), EuroPrice.ofCents(500), EuroPrice.ofCents(500)),
                                        2
                                )
                        ),
                        EuroPrice.ofCents(6000L),
                        "itea \nChair \"Elsa\" 30,00\u00A0€ * 2\nTotal 60,00\u00A0€"
                ),
                Arguments.of(
                        Order.of(
                                new Order.OrderItem(
                                        new Table(ProductId.random(), ProductName.TABLE_LOLA.displayName(), EuroPrice.ofCents(2000), EuroPrice.ofCents(5000)),
                                        2
                                )
                        ),
                        EuroPrice.ofCents(26000L),
                        "itea \nTable \"Lola\" 130,00\u00A0€ * 2\nTotal 260,00\u00A0€"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideProductsAndExpectedReceipts")
    void shouldReturnCorrectReceipts(Order order, EuroPrice expectedTotalPrice, String expectedReceiptText) {
        var receipt = receiptPresenter.prepareReceipt(order);

        then(receipt.priceInCents()).isEqualTo(expectedTotalPrice.asCents());
        then(receipt.text()).isEqualTo(expectedReceiptText);
    }
}
