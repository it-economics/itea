package com.ite.itea.retail.adapters.in.controller;

import java.util.List;

public record OrderRequest(List<ItemRequest> itemRequests) {

}
