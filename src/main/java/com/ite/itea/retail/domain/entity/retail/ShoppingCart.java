package com.ite.itea.retail.domain.entity.retail;

import com.ite.itea.retail.domain.entity.core.EuroPrice;

import java.util.ArrayList;

public final class ShoppingCart {

    private final ArrayList<CartItem> cartItems = new ArrayList<>();

    public ShoppingCart() {
    }

    public static ShoppingCart empty() {
        return new ShoppingCart();
    }

    public void add(Product product) {
        cartItems.add(new CartItem(product));
    }

    public EuroPrice price() {
        return cartItems.stream()
                .map(cartItem -> cartItem.product.price())
                .reduce(EuroPrice.zero(), EuroPrice::plus);
    }

    private record CartItem(Product product) {
    }
}
