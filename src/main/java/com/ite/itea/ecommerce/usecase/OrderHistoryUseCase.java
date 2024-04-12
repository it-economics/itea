package com.ite.itea.ecommerce.usecase;

import com.ite.itea.ecommerce.usecase.dto.ItemRequest;
import com.ite.itea.ecommerce.usecase.dto.OrderRequest;
import com.ite.itea.ecommerce.usecase.port.OrderHistoryRepository;

import java.util.List;

public class OrderHistoryUseCase {

    private final OrderHistoryRepository orderHistoryRepository;

    public OrderHistoryUseCase(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    public void addItemForUser(OrderRequest orderRequest, String userid) {
        orderRequest.itemRequests().forEach(itemRequest -> orderHistoryRepository.addItem(itemRequest, userid));
    }

    public List<ItemRequest> getOrderHistoryForUser(String userid) {
        return orderHistoryRepository.getAllItemsForUser(userid);
    }
}
