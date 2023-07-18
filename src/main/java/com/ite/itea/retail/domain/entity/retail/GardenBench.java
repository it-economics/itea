package com.ite.itea.retail.domain.entity.retail;

import com.ite.itea.retail.domain.entity.core.EuroPrice;

public class GardenBench extends Product {

    private static final EuroPrice DEFAULT_ELEMENT_PRICE = EuroPrice.ofEuros(80);
    private static final EuroPrice PLANT_ELEMENT_PRICE = EuroPrice.ofEuros(130);
    private static final EuroPrice WOOD_PLATE_PRICE = EuroPrice.ofEuros(70);
    private static final EuroPrice BACKREST_PRICE = EuroPrice.ofEuros(50);

    private static final int STANDARD_LENGTH_IN_CM = 165;
    private static final EuroPrice EXTRA_LENGTH_FEE_PER_CENTIMETER = EuroPrice.ofEuros(1);

    private final int lengthInCentimeters;
    private final int amountDefaultElements;
    private final int amountPlantElements;
    private final boolean hasBackrest;
    private final boolean isDelivery;

    public GardenBench(ProductId id, int lengthInCentimeters, int amountDefaultElements, int amountPlantElements, boolean hasBackrest, boolean isDelivery) {
        super(id, "Garden bench");
        this.lengthInCentimeters = lengthInCentimeters;
        this.amountDefaultElements = amountDefaultElements;
        this.amountPlantElements = amountPlantElements;
        this.hasBackrest = hasBackrest;
        this.isDelivery = isDelivery;
    }

    @Override
    public EuroPrice price() {
        final EuroPrice productPrice = calculateProductPrice();
        final EuroPrice deliveryPrice = calculateDeliveryPrice();
        return productPrice.plus(deliveryPrice);
    }

    @Override
    public String description() {
        final EuroPrice productPrice = calculateProductPrice();
        final EuroPrice deliveryPrice = calculateDeliveryPrice();
        final EuroPrice totalPriceIncludingDelivery = productPrice.plus(deliveryPrice);
        return "Order for a garden bench:\n"
                + formatElementsText(amountPlantElements, hasBackrest)
                + "Total length: " + totalLength(amountPlantElements, lengthInCentimeters) + " cm\n"
                + formatDeliveryText(isDelivery, deliveryPrice)
                + formatDeliveryPriceText(productPrice, totalPriceIncludingDelivery);
    }

    private EuroPrice calculateProductPrice() {
        return DEFAULT_ELEMENT_PRICE.times(amountDefaultElements)
                .plus(PLANT_ELEMENT_PRICE.times(amountPlantElements))
                .plus(extraLengthPrice())
                .plus(WOOD_PLATE_PRICE)
                .plus(hasBackrest ? BACKREST_PRICE : EuroPrice.zero());
    }

    private EuroPrice calculateDeliveryPrice() {
        if (!isDelivery) {
            return EuroPrice.zero();
        }

        if (lengthInCentimeters <= 200) {
            return switch (amountDefaultElements) {
                case 2 -> EuroPrice.ofEuros(70);
                case 1 -> EuroPrice.ofEuros(80);
                case 0 -> EuroPrice.ofEuros(90);
                default -> EuroPrice.ofEuros(130);
            };
        } else {
            return switch (amountDefaultElements) {
                case 2 -> EuroPrice.ofEuros(100);
                case 1 -> EuroPrice.ofEuros(110);
                case 0 -> EuroPrice.ofEuros(120);
                default -> EuroPrice.ofEuros(130);
            };
        }
    }

    private EuroPrice extraLengthPrice() {
        if (lengthInCentimeters > STANDARD_LENGTH_IN_CM) {
            return EXTRA_LENGTH_FEE_PER_CENTIMETER.times(lengthInCentimeters - STANDARD_LENGTH_IN_CM);
        }
        return EuroPrice.zero();
    }

    private String formatElementsText(int amountPlantElements, boolean hasBackrest) {
        final var amountPlantElementsText = switch (amountPlantElements) {
            case 1 -> "Elements: 1 of 2 elements is a plant element";
            case 2 -> "Elements: 2 of 2 elements is a plant element";
            default -> "Elements: 0 of 2 elements is a plant element";
        };
        final var backRestText = hasBackrest
                ? ", has a backrest\n"
                : ", has no backrest\n";
        return amountPlantElementsText + backRestText;
    }

    private String formatDeliveryText(boolean isDelivery, EuroPrice deliveryPrice) {
        String deliveryText = isDelivery
                ? "Delivery Type: Product is delivered "
                : "Delivery Type: Product is collected ";
        return deliveryText + "for " + deliveryPrice.formatPrice() + "\n";
    }

    private String formatDeliveryPriceText(EuroPrice priceWithoutDelivery, EuroPrice priceIncludingDelivery) {
        if (isDelivery) {
            return "Total price (without delivery): " + priceWithoutDelivery.formatPrice() + "\n"
                    + "Total price (including delivery): " + priceIncludingDelivery.formatPrice() + "\n";
        } else {
            return "Total price: " + priceWithoutDelivery.formatPrice() + "\n";
        }
    }

    private int totalLength(int amountPlantElements, int length) {
        return switch (amountPlantElements) {
            case 1 -> length + 60;
            case 2 -> length + 108;
            default -> length + 16;
        };
    }
}
