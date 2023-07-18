package com.ite.itea.retail.domain.entity.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> all();
    Optional<User> byLastName(String lastName);
}
