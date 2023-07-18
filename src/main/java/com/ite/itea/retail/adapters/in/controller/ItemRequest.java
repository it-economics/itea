package com.ite.itea.retail.adapters.in.controller;

import com.ite.itea.retail.domain.entity.retail.ProductName;

public record ItemRequest(ProductName name, int amount, long price) {

}
