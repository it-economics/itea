package com.ite.itea.retail.domain.entity.core;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public final class EuroPrice {

    /**
     * Note: We *could* use BigDecimal instead, and in fact we might later decide to do so.
     * The important thing is that this is an implementation detail, and nothing externally depends
     * on this detail. BigDecimal itself, however, is not a monetary type (although it can be used
     * to implement one), but a numerical type. Whether negative prices should be allowed, for
     * example, and how many decimal places we want to support (i.e., fractional cents) depends on
     * the domain (i.e., the business rules).
     */
    private final long cents;

    private EuroPrice(long cents) {
        if (cents < 0) {
            throw new NegativePriceException(cents);
        }
        this.cents = cents;
    }

    public static EuroPrice ofCents(long cents) {
        return new EuroPrice(cents);
    }

    public static EuroPrice zero() {
        return new EuroPrice(0);
    }

    public static EuroPrice ofEurosAndCents(int euros, int cents) {
        final var totalCents = euros * 100 + cents;
        return new EuroPrice(totalCents);
    }

    public static EuroPrice ofEuros(int euros) {
        final var totalCents = euros * 100;
        return new EuroPrice(totalCents);
    }

    public EuroPrice plus(EuroPrice priceToAdd) {
        return new EuroPrice(cents + priceToAdd.cents);
    }

    public EuroPrice times(int i) {
        return new EuroPrice(cents * i);
    }

    public String formatPrice() {
        var currencyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        var decimalPrice = BigDecimal.valueOf(cents).movePointLeft(2);
        return currencyFormat.format(decimalPrice);
    }

    public long asCents() {
        return cents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroPrice euroPrice = (EuroPrice) o;
        return cents == euroPrice.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    @Override
    public String toString() {
        return formatPrice();
    }

    public static class NegativePriceException extends RuntimeException {

        public NegativePriceException(long amountInCents) {
            super("Failed to instantiate EuroPrice due to negative amount: " + amountInCents + " cents");
        }
    }
}
