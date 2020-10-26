package ru.yvzhelnin.otus.catalog.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Кастомизация бинов для сбора метрик
 */
@Configuration
public class MonitoringConfig {

    /**
     * Конфигурирование переменной, содержащей название приложения, для корректного отображения в мониторинге
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "Catalog Application");
    }
}
