package ru.yvzhelnin.otus.cart.service.impl;

import org.springframework.stereotype.Service;
import ru.yvzhelnin.otus.cart.dto.CartItemDto;
import ru.yvzhelnin.otus.cart.repository.CartRepository;
import ru.yvzhelnin.otus.cart.service.CartService;

import java.io.IOException;
import java.util.Collection;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void saveOrUpdate(Collection<CartItemDto> cartItemDtos) throws IOException {
        cartRepository.saveOrUpdate(cartItemDtos);
    }

    @Override
    public Collection<CartItemDto> get() throws IOException {
        return cartRepository.get();
    }

    @Override
    public void delete() {
        cartRepository.delete();
    }
}
