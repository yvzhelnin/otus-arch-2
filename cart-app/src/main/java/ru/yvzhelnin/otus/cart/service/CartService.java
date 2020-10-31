package ru.yvzhelnin.otus.cart.service;

import ru.yvzhelnin.otus.cart.dto.CartItemDto;

import java.io.IOException;
import java.util.Collection;

public interface CartService {

    void saveOrUpdate(Collection<CartItemDto> cartItemDtos) throws IOException;

    Collection<CartItemDto> get() throws IOException;

    void delete();
}
