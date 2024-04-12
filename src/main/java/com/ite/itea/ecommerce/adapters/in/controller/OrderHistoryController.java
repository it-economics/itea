package com.ite.itea.ecommerce.adapters.in.controller;

import com.ite.itea.ecommerce.adapters.out.persistence.InMemoryOrderHistoryRepository;
import com.ite.itea.ecommerce.usecase.OrderHistoryUseCase;
import com.ite.itea.ecommerce.usecase.dto.ItemRequest;
import com.ite.itea.ecommerce.usecase.dto.OrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Tag(name = "OrderHistory")
public class OrderHistoryController {

    private final OrderHistoryUseCase orderHistoryUseCase = new OrderHistoryUseCase(
            new InMemoryOrderHistoryRepository()
    );

    @Operation(summary = "adds items to the order history", description = "adds items to the order history")
    @ResponseBody
    @PostMapping(path = "/orderhistory/{userid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addItemForUser(@RequestBody OrderRequest orderRequest, @PathVariable String userid) {
        orderHistoryUseCase.addItemForUser(orderRequest, userid);
    }

    @Operation(summary = "returns all ordered items", description = "returns all ordered items")
    @ResponseBody
    @GetMapping(path = "/orderhistory/{userid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemRequest> addItem(@PathVariable String userid) {
        return orderHistoryUseCase.getOrderHistoryForUser(userid);
    }

}
