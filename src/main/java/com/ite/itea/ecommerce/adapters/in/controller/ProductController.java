package com.ite.itea.ecommerce.adapters.in.controller;

import com.ite.itea.ecommerce.adapters.out.persistence.InMemoryProductRepository;
import com.ite.itea.ecommerce.domain.retail.Product;
import com.ite.itea.ecommerce.domain.retail.ProductId;
import com.ite.itea.ecommerce.usecase.FindAProductUseCase;
import com.ite.itea.ecommerce.usecase.GetProductsUseCase;
import com.ite.itea.ecommerce.usecase.dto.ProductResponse;
import com.ite.itea.ecommerce.usecase.dto.ProductsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@Tag(name = "Product")
public class ProductController {

    private final GetProductsUseCase getProductsUseCase = new GetProductsUseCase(
            new InMemoryProductRepository()
    );

    private final FindAProductUseCase findAProductUseCase = new FindAProductUseCase(
            new InMemoryProductRepository()
    );


    @Operation(summary = "returns all products", description = "returns all products from the data store")
    @ResponseBody
    @GetMapping(path = "/products",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductsResponse findAllProducts() {
        final List<Product> products = getProductsUseCase.execute();
        return new ProductsResponse(products);
    }

    @Operation(summary = "returns one product", description = "returns a specific product")
    @ResponseBody
    @GetMapping(path = "/product/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse findOneProduct(@PathVariable String id) {
        final Optional<Product> product = findAProductUseCase.execute(new ProductId(id));

        return product.map(ProductResponse::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

}
