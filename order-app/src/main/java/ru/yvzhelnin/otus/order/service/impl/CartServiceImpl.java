package ru.yvzhelnin.otus.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import ru.yvzhelnin.otus.order.service.CartService;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Value("${order.cart-app.url.clear}")
    private String clearCartUrl;

    private final RestTemplate restTemplate;

    public CartServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void clear() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        LOGGER.info("Cleaning up the cart for the customer with sessionId = {}", sessionId);
        HttpEntity requestEntity = new HttpEntity<>(new HttpHeaders());
        final String url = clearCartUrl + "/" + sessionId;
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, new HashMap<>());

        LOGGER.info("The cart has been deleted");
    }
}
