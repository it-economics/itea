package com.ite.itea.retail.domain.usecase;

import com.ite.itea.retail.adapters.out.presenter.Receipt;
import com.ite.itea.retail.domain.entity.retail.Order;

public interface ReceiptPresenter {
    Receipt prepareReceipt(Order order);
}
