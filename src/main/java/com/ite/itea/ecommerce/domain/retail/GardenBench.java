package com.ite.itea.ecommerce.domain.retail;

import com.ite.itea.ecommerce.domain.core.EuroPrice;

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
    private final boolean shouldBeDelivered;

    public GardenBench(ProductId id, int lengthInCentimeters, int amountDefaultElements, int amountPlantElements, boolean hasBackrest, boolean shouldBeDelivered) {
        super(id, "Garden bench");
        this.lengthInCentimeters = lengthInCentimeters;
        this.amountDefaultElements = amountDefaultElements;
        this.amountPlantElements = amountPlantElements;
        this.hasBackrest = hasBackrest;
        this.shouldBeDelivered = shouldBeDelivered;
    }

    @Override
    public EuroPrice price() {
        final EuroPrice productPrice = DEFAULT_ELEMENT_PRICE.times(amountDefaultElements)
                .plus(PLANT_ELEMENT_PRICE.times(amountPlantElements))
                .plus(extraLengthPrice())
                .plus(WOOD_PLATE_PRICE)
                .plus(hasBackrest ? BACKREST_PRICE : EuroPrice.zero());
        final EuroPrice deliveryPrice = calculateDeliveryPrice();
        return productPrice.plus(deliveryPrice);
    }

    @Override
    public String description() {

        int totalLength;
        if (amountPlantElements == 1) {
            totalLength = lengthInCentimeters + 60;
        } else {
            if (amountPlantElements == 2) {
                totalLength = lengthInCentimeters + 108;
            } else {
                totalLength = lengthInCentimeters + 16;
            }
        }
        final EuroPrice productPrice = DEFAULT_ELEMENT_PRICE.times(amountDefaultElements)
                .plus(PLANT_ELEMENT_PRICE.times(amountPlantElements))
                .plus(extraLengthPrice())
                .plus(WOOD_PLATE_PRICE)
                .plus(hasBackrest ? BACKREST_PRICE : EuroPrice.zero());
        final EuroPrice deliveryPrice = calculateDeliveryPrice();
        final EuroPrice totalPriceIncludingDelivery = productPrice.plus(deliveryPrice);

        String formattedDeliveryPrice;
        if (shouldBeDelivered) {
            formattedDeliveryPrice = "Total price (without delivery): " + productPrice.formatPrice() + "\n"
                    + "Total price (including delivery): " + totalPriceIncludingDelivery.formatPrice() + "\n";
        } else {
            formattedDeliveryPrice = "Total price: " + productPrice.formatPrice() + "\n";
        }

        String formattedDeliveryText;
        if (shouldBeDelivered) {
            formattedDeliveryText = "Delivery Type: Product is delivered for " + deliveryPrice.formatPrice() + "\n";
        } else {
            formattedDeliveryText = "Delivery Type: Product is collected for " + deliveryPrice.formatPrice() + "\n";
        }

        String formattedElementText;

        if (amountPlantElements==1) {
            formattedElementText = "Elements: 1 of 2 elements is a plant element";
        } else if (amountPlantElements==2) {
            formattedElementText = "Elements: 2 of 2 elements is a plant element";
        }  else {
            formattedElementText = "Elements: 0 of 2 elements is a plant element";
        }
        formattedElementText += hasBackrest ? ", has a backrest\n" : ", has no backrest\n";

        return "Order for a garden bench:\n"
                + formattedElementText
                + "Total length: " + totalLength + " cm\n"
                + formattedDeliveryText
                + formattedDeliveryPrice;
    }

    private EuroPrice calculateDeliveryPrice() {
        EuroPrice deliveryPrice;
        if (shouldBeDelivered) {
            if (lengthInCentimeters <= 200 && amountDefaultElements == 2) {
                deliveryPrice = EuroPrice.ofEuros(70); //delivery price two defaultElements, standard length
            } else if (lengthInCentimeters <= 200 && amountDefaultElements == 1) {
                deliveryPrice = EuroPrice.ofEuros(80); //delivery price single defaultElement, standard length
            } else if (lengthInCentimeters <= 200 && amountDefaultElements == 0) {
                deliveryPrice = EuroPrice.ofEuros(90); //delivery price no defaultElements, standard length
            } else if (lengthInCentimeters <= 200) {
                deliveryPrice = EuroPrice.ofEuros(130); //default delivery price >=3 defaultElements, standard length
            } else if (amountDefaultElements == 2) {
                deliveryPrice = EuroPrice.ofEuros(100); //delivery price two defaultElements, oversize
            } else if (amountDefaultElements == 1) {
                deliveryPrice = EuroPrice.ofEuros(110); //delivery price single defaultElement, oversize
            } else if (amountDefaultElements == 0) {
                deliveryPrice = EuroPrice.ofEuros(120); //delivery price no defaultElements, oversize
            } else {
                deliveryPrice = EuroPrice.ofEuros(130); //delivery price >=3 defaultElements, oversize
            }
        } else {
            deliveryPrice = EuroPrice.zero();
        }
        return deliveryPrice;
    }

    private EuroPrice extraLengthPrice() {
        if (lengthInCentimeters > STANDARD_LENGTH_IN_CM) {
            return EXTRA_LENGTH_FEE_PER_CENTIMETER.times(lengthInCentimeters - STANDARD_LENGTH_IN_CM);
        }
        return EuroPrice.zero();
    }


}
