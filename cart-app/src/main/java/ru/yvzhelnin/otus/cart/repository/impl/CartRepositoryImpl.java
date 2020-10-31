package ru.yvzhelnin.otus.cart.repository.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import ru.yvzhelnin.otus.cart.dto.CartItemDto;
import ru.yvzhelnin.otus.cart.repository.CartRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private static final int TTL_SECONDS = 60;

    private final MemcachedClient memcachedClient;
    private final ObjectMapper objectMapper;

    public CartRepositoryImpl(MemcachedClient memcachedClient, ObjectMapper objectMapper) {
        this.memcachedClient = memcachedClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveOrUpdate(Collection<CartItemDto> cartItemDtos) throws IOException {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        String newValue = objectMapper.writeValueAsString(cartItemDtos);
        memcachedClient.set(sessionId, TTL_SECONDS, newValue);
    }

    @Override
    public Collection<CartItemDto> get() throws IOException {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Object existingValue = memcachedClient.get(sessionId);
        if (existingValue != null) {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, CartItemDto.class);
            return objectMapper.readValue(existingValue.toString(), type);
        }
        return null;
    }

    @Override
    public void delete() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        memcachedClient.delete(sessionId);
    }
}
