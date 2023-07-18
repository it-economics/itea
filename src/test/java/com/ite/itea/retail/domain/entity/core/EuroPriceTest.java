package com.ite.itea.retail.domain.entity.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EuroPriceTest {

    /**
     * Remember that unit tests, especially in TDD, are also documentation and serve as usage
     * examples, not only as heuristic of correctness.
     * So instead of creating many individual test methods, in some cases it can be easier to
     * understand and maintain if we simply list the different use cases and parameterize the test.
     * Each of the following cases can be seen as a "unit".
     * This illustrates why having "one test for each method" is not a guarantee of good coverage,
     * and can often lead to worse test suites.
     * Go ahead, try adding a "unit of behavior", in TDD fashion, where the EuroPrice is
     * instantiated from a BigDecimal: `EuroPrice.fromBigDecimal()`
     */
    private static Stream<Arguments> provideEqualPrices() {
        return Stream.of(
                Arguments.of(
                        EuroPrice.zero(),
                        EuroPrice.ofCents(0)
                ),
                Arguments.of(
                        EuroPrice.ofEurosAndCents(0, 42),
                        EuroPrice.ofCents(42)
                ),
                Arguments.of(
                        EuroPrice.ofEurosAndCents(2, 42),
                        EuroPrice.ofCents(242)
                ),
                Arguments.of(
                        // This case might not be obvious, so it is good to have it as an explicit
                        // example. While probably unusual in practice, there is no reason not to
                        // allow this usage where the number of cents is greater than 99 and thus
                        // overflows to the euros part.
                        EuroPrice.ofEurosAndCents(1, 142),
                        EuroPrice.ofCents(242)
                ),
                Arguments.of(
                        EuroPrice.ofEuros(12),
                        EuroPrice.ofCents(1200)
                ),
                Arguments.of(
                        EuroPrice.ofCents(10).plus(EuroPrice.ofCents(42)),
                        EuroPrice.ofCents(9).plus(EuroPrice.ofCents(43))
                ),
                Arguments.of(
                        EuroPrice.zero().plus(EuroPrice.zero()),
                        EuroPrice.ofCents(0)
                ),
                Arguments.of(
                        EuroPrice.zero().times(13),
                        EuroPrice.ofCents(0)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideEqualPrices")
    void shouldBeEqual(EuroPrice price1, EuroPrice price2) {
        assertThat(price1).isEqualTo(price2);
    }

    /**
     * We don't need many cases just to make sure that `equals()` does not always return true.
     * We especially don't want to duplicate the usage examples ("units of behavior") from above.
     * The unit of behavior here is simply "different prices are not equal".
     */
    @Test
    void shouldBeNotEqual() {
        assertThat(EuroPrice.ofEurosAndCents(13, 37))
                .isNotEqualTo(EuroPrice.ofCents(42));
    }

    @Test
    void shouldFormatCorrectly() {
        assertThatExceptionOfType(EuroPrice.NegativePriceException.class)
                .isThrownBy(() -> EuroPrice.ofCents(-42));
    }

    private static Stream<Arguments> providePricesWithExpectedFormattedString() {
        return Stream.of(
                Arguments.of(EuroPrice.zero(), "0,00\u00A0€"),
                Arguments.of(EuroPrice.ofEurosAndCents(13, 37), "13,37\u00A0€")
        );
    }

    @ParameterizedTest
    @MethodSource("providePricesWithExpectedFormattedString")
    void shouldFormatCorrectly(EuroPrice price, String expectedFormatted) {
        assertThat(price.formatPrice()).isEqualTo(expectedFormatted);
    }
}
