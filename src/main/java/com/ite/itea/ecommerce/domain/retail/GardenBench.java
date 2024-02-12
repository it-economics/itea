package com.ite.itea.ecommerce.domain.retail;

import com.ite.itea.ecommerce.domain.core.EuroPrice;

public class GardenBench extends Product {

    private static final EuroPrice DEFAULT_ELEMENT_PR = EuroPrice.ofEuros(80);
    private static final EuroPrice PLANT_ELEMENT_PR = EuroPrice.ofEuros(130);
    private static final EuroPrice WOOD_PLATE_PR = EuroPrice.ofEuros(70);
    private static final EuroPrice BACKREST_PR = EuroPrice.ofEuros(50);

    private static final int STANDARD_LENGTH = 165; //cm
    private static final EuroPrice EXTRA_LENGTH_FEE = EuroPrice.ofEuros(1); //per cm

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
        final EuroPrice basePrice = DEFAULT_ELEMENT_PR.times(amountDefaultElements)
                .plus(PLANT_ELEMENT_PR.times(amountPlantElements))
                .plus(extraLengthPrice())
                .plus(WOOD_PLATE_PR)
                .plus(hasBackrest ? BACKREST_PR : EuroPrice.zero());
        final EuroPrice additionalPrice = calculateDeliveryPrice();
        return basePrice.plus(additionalPrice);
    }

    @Override
    public String description() {

        int value; //calculates the total length
        if (amountPlantElements == 1) {
            value = lengthInCentimeters + 60;
        } else {
            if (amountPlantElements == 2) {
                value = lengthInCentimeters + 108;
            } else {
                value = lengthInCentimeters + 16;
            }
        }
        final EuroPrice price = DEFAULT_ELEMENT_PR.times(amountDefaultElements)
                .plus(PLANT_ELEMENT_PR.times(amountPlantElements))
                .plus(extraLengthPrice())
                .plus(WOOD_PLATE_PR)
                .plus(hasBackrest ? BACKREST_PR : EuroPrice.zero());
        final EuroPrice fee = calculateDeliveryPrice();
        final EuroPrice totalPriceIncludingDelivery = price.plus(fee);

        String priceText;
        if (shouldBeDelivered) {
            priceText = "Total price (without delivery): " + price.formatPrice() + "\n"
                    + "Total price (including delivery): " + totalPriceIncludingDelivery.formatPrice() + "\n";
        } else {
            priceText = "Total price: " + price.formatPrice() + "\n";
        }

        String feeText;
        if (shouldBeDelivered) {
            feeText = "Delivery Type: Product is delivered for " + fee.formatPrice() + "\n";
        } else {
            feeText = "Delivery Type: Product is collected for " + fee.formatPrice() + "\n";
        }

        String formattedElement;
        if (amountPlantElements==1) {
            formattedElement = "Elements: 1 of 2 elements is a plant element";
        } else if (amountPlantElements==2) {
            formattedElement = "Elements: 2 of 2 elements is a plant element";
        }  else {
            formattedElement = "Elements: 0 of 2 elements is a plant element";
        }
        formattedElement += hasBackrest ? ", has a backrest\n" : ", has no backrest\n";

        return "Order for a garden bench:\n"
                + formattedElement
                + "Total length: " + value + " cm\n"
                + feeText
                + priceText;
    }

    private EuroPrice calculateDeliveryPrice() {
        EuroPrice price;
        if (shouldBeDelivered) {
            if (lengthInCentimeters <= 200 && amountDefaultElements == 2) {
                price = EuroPrice.ofEuros(70); //delivery price two defaultElements, standard length
            } else if (lengthInCentimeters <= 200 && amountDefaultElements == 1) {
                price = EuroPrice.ofEuros(80); //delivery price single defaultElement, standard length
            } else if (lengthInCentimeters <= 200 && amountDefaultElements == 0) {
                price = EuroPrice.ofEuros(90); //delivery price no defaultElements, standard length
            } else if (lengthInCentimeters <= 200) {
                price = EuroPrice.ofEuros(130); //default delivery price >=3 defaultElements, standard length
            } else if (amountDefaultElements == 2) {
                price = EuroPrice.ofEuros(100); //delivery price two defaultElements, oversize
            } else if (amountDefaultElements == 1) {
                price = EuroPrice.ofEuros(110); //delivery price single defaultElement, oversize
            } else if (amountDefaultElements == 0) {
                price = EuroPrice.ofEuros(120); //delivery price no defaultElements, oversize
            } else {
                price = EuroPrice.ofEuros(130); //delivery price >=3 defaultElements, oversize
            }
        } else {
            price = EuroPrice.zero();
        }
        return price;
    }

    private EuroPrice extraLengthPrice() {
        if (lengthInCentimeters > STANDARD_LENGTH) {
            return EXTRA_LENGTH_FEE.times(lengthInCentimeters - STANDARD_LENGTH);
        }
        return EuroPrice.zero();
    }


}
