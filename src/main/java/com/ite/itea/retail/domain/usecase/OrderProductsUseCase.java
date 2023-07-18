package com.ite.itea.retail.domain.usecase;

import com.ite.itea.retail.adapters.in.controller.OrderRequest;
import com.ite.itea.retail.adapters.out.presenter.Receipt;
import com.ite.itea.retail.domain.entity.retail.Order;
import com.ite.itea.retail.domain.entity.retail.ProductId;

import java.util.ArrayList;
import java.util.List;

public class OrderProductsUseCase {

    private final ProductRepository productRepository;
    private final ReceiptPresenter receiptPresenter;

    public OrderProductsUseCase(ProductRepository productRepository, ReceiptPresenter receiptPresenter) {
        this.productRepository = productRepository;
        this.receiptPresenter = receiptPresenter;
    }

    public Receipt execute(OrderRequest orderRequest) {
        final List<Order.OrderItem> orderItems = new ArrayList<>();

        for (var itemRequest : orderRequest.itemRequests()) {
            var productId = new ProductId(itemRequest.name().name());
            var product = productRepository.byId(productId);
            product.ifPresent(value ->
                    orderItems.add(new Order.OrderItem(value, itemRequest.amount())));
        }

        return receiptPresenter.prepareReceipt(new Order(orderItems));
    }

}
