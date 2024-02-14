package com.ite.itea.ecommerce.adapters.out.presenter;

import com.ite.itea.ecommerce.domain.retail.Order;
import com.ite.itea.ecommerce.domain.core.EuroPrice;
import com.ite.itea.ecommerce.usecase.dto.Receipt;

import java.text.MessageFormat;
import java.util.stream.Collectors;

public class ReceiptPresenter implements com.ite.itea.ecommerce.usecase.port.ReceiptPresenter {

    @Override
    public Receipt prepareReceipt(Order order) {
        String totalText = "";
        for (Order.OrderItem orderItem : order.items()) {
            String text;
            if (orderItem.amount() == 0) {
                text = "";
            } else {
                text = MessageFormat.format("{0} {1} * {2}\n", orderItem.product().name(), orderItem.product().price().formatPrice(), orderItem.amount());
            }
            totalText += text;
        }

        EuroPrice totalPrice = EuroPrice.zero();
        for (Order.OrderItem orderItem : order.items()) {
            totalPrice = totalPrice.plus(orderItem.product().price().times(orderItem.amount()));
        }

        totalText = "itea \n" + totalText + "Total " + totalPrice.formatPrice();

        return new Receipt(totalPrice.asCents(), totalText);
    }

}
