package ru.yvzhelnin.otus.cart.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.cart.dto.CartItemDto;
import ru.yvzhelnin.otus.cart.service.CartService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Collection<CartItemDto> getCart() throws IOException {
        return cartService.get();
    }

    @PostMapping
    public void saveOrUpdateCart(Collection<CartItemDto> cartItemDtos) throws IOException {
        cartService.saveOrUpdate(cartItemDtos);
    }

    @DeleteMapping("/{sessionId}")
    public void deleteCart(@PathVariable("sessionId") String sessionId) {
        cartService.delete(sessionId);
    }
}
