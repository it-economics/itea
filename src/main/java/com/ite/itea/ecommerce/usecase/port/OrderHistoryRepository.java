package com.ite.itea.ecommerce.usecase.port;

import com.ite.itea.ecommerce.domain.retail.Product;
import com.ite.itea.ecommerce.domain.retail.ProductId;
import com.ite.itea.ecommerce.usecase.dto.ItemRequest;

import java.util.List;
import java.util.Optional;

public interface OrderHistoryRepository {

    List<ItemRequest> getAllItemsForUser(String userid) ;

    void addItem(ItemRequest itemRequest, String userId);
}
