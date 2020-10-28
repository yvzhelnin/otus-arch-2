package ru.yvzhelnin.otus.cart.config;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
public class MemcachedConfig {

    @Value("${cart.cache.host}")
    private String cartCacheHost;

    @Value("${cart.cache.port}")
    private Integer cartCachePort;

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(cartCacheHost, cartCachePort);

        return new MemcachedClient(socketAddress);
    }
}
