package com.ite.itea.ecommerce.integration.in.controller;

import com.ite.itea.ecommerce.usecase.dto.ItemRequest;
import com.ite.itea.ecommerce.usecase.dto.OrderRequest;
import com.ite.itea.ecommerce.usecase.dto.ProductsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@TestPropertySource(properties = {"management.port=0"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int actuatorPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnCorrectReceiptWhenSendingRequestWithAPictureOsloToTheController() {
        var orderRequest = new OrderRequest(List.of(new ItemRequest("2", 2)));

        var entity = this.testRestTemplate.getForEntity("http://localhost:" + this.port + "/product", ProductsResponse.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody().products().get(0).price()).isEqualTo(1998L);
    }
}
