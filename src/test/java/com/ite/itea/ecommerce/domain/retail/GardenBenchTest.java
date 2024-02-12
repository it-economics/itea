package com.ite.itea.ecommerce.domain.retail;

import com.ite.itea.ecommerce.domain.core.EuroPrice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GardenBenchTest {

    private static Stream<Arguments> provideGardenBenchConfigurationsWithRespectivePriceAndDescription() {
        return Stream.of(
                Arguments.of(
                        new GardenBench(ProductId.random(), 165, 2, 0, false, false),
                        EuroPrice.ofCents(23000),
                        """
                                Order for a garden bench:
                                Elements: 0 of 2 elements is a plant element, has no backrest
                                Total length: 181 cm
                                Delivery Type: Product is collected for 0,00\u00A0€
                                Total price: 230,00\u00A0€
                                """
                ),
                Arguments.of(
                        new GardenBench(ProductId.random(), 165, 2, 0, false, true),
                        EuroPrice.ofCents(30000),
                        """
                                Order for a garden bench:
                                Elements: 0 of 2 elements is a plant element, has no backrest
                                Total length: 181 cm
                                Delivery Type: Product is delivered for 70,00\u00A0€
                                Total price (without delivery): 230,00\u00A0€
                                Total price (including delivery): 300,00\u00A0€
                                """
                ),
                Arguments.of(
                        new GardenBench(ProductId.random(), 165, 2, 0, false, false),
                        EuroPrice.ofCents(23000),
                        """
                                Order for a garden bench:
                                Elements: 0 of 2 elements is a plant element, has no backrest
                                Total length: 181 cm
                                Delivery Type: Product is collected for 0,00\u00A0€
                                Total price: 230,00\u00A0€
                                """
                ),
                Arguments.of(
                        new GardenBench(ProductId.random(), 165, 1, 1, false, false),
                        EuroPrice.ofCents(28000),
                        """
                                Order for a garden bench:
                                Elements: 1 of 2 elements is a plant element, has no backrest
                                Total length: 225 cm
                                Delivery Type: Product is collected for 0,00\u00A0€
                                Total price: 280,00\u00A0€
                                """
                ),
                Arguments.of(
                        new GardenBench(ProductId.random(), 165, 2, 0, true, false),
                        EuroPrice.ofCents(28000),
                        """
                                Order for a garden bench:
                                Elements: 0 of 2 elements is a plant element, has a backrest
                                Total length: 181 cm
                                Delivery Type: Product is collected for 0,00\u00A0€
                                Total price: 280,00\u00A0€
                                """
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideGardenBenchConfigurationsWithRespectivePriceAndDescription")
    void shouldProvideCorrectPriceAndDescription(GardenBench gardenBench, EuroPrice expectedPrice, String description) {
        assertThat(gardenBench.price()).isEqualTo(expectedPrice);
        assertThat(gardenBench.description()).isEqualTo(description);
    }
}
