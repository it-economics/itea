package com.ite.itea.ecommerce.adapters.out.persistence;

import com.ite.itea.ecommerce.usecase.dto.ItemRequest;
import com.ite.itea.ecommerce.usecase.port.OrderHistoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOrderHistoryRepository implements OrderHistoryRepository {

    private final Map<String, List<ItemRequest>> orderHistoriesForAllUsers = new HashMap<>();

    @Override
    public List<ItemRequest> getAllItemsForUser(String userid) {
        return orderHistoriesForAllUsers.getOrDefault(userid, new ArrayList<>());
    }

    @Override
    public void addItem(ItemRequest itemRequest, String userId) {
        if (orderHistoriesForAllUsers.containsKey(userId)) {
            orderHistoriesForAllUsers.get(userId).add(itemRequest);
        } else {
            List<ItemRequest> items = new ArrayList<>();
            items.add(itemRequest);
            orderHistoriesForAllUsers.put(userId, items);
        }
    }
}
