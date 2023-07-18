package com.ite.itea.retail.domain.entity.user;

import java.util.List;

public record User(String firstname, String lastname, List<String> purchasedItems) {

    public String formatFullName() {
        return firstname() + " " + lastname();
    }
}
