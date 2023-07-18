package com.ite.itea.retail.adapters.in.controller;

import com.ite.itea.retail.adapters.out.presenter.Receipt;
import com.ite.itea.retail.adapters.out.persistence.InMemoryProductRepository;
import com.ite.itea.retail.domain.usecase.OrderProductsUseCase;
import com.ite.itea.retail.adapters.out.presenter.ReceiptPresenter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckoutController {

    private final OrderProductsUseCase orderProductsUseCase = new OrderProductsUseCase(
            new InMemoryProductRepository(),
            new ReceiptPresenter()
    );

    @ResponseBody
    @PostMapping(path = "/checkout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReceiptResponse calculate(@RequestBody OrderRequest orderRequest) {
        final Receipt receipt = orderProductsUseCase.execute(orderRequest);
        return new ReceiptResponse(receipt.priceInCents(), receipt.text());
    }
}
