package ru.yvzhelnin.otus.returnal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yvzhelnin.otus.returnal.enums.DeliveryStatus;
import ru.yvzhelnin.otus.returnal.model.DeliveryInfo;
import ru.yvzhelnin.otus.returnal.repository.DeliveryInfoRepository;
import ru.yvzhelnin.otus.returnal.service.ReturnService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Value("${delivery.app.url.return}")
    private String returnUrl;

    private final DeliveryInfoRepository deliveryInfoRepository;

    public ReturnServiceImpl(DeliveryInfoRepository deliveryInfoRepository) {
        this.deliveryInfoRepository = deliveryInfoRepository;
    }

    @Scheduled(cron = "@hourly")
    @Override
    public void distributeReturnDeliveries() {
        List<Long> ids = deliveryInfoRepository.findAllByDeliveryStatusAndReturnDateAndCourierIsNull(DeliveryStatus.ISSUED,
                LocalDate.now().plusDays(1)).stream()
                .map(DeliveryInfo::getId)
                .collect(Collectors.toList());

        Map<String, String> parameters = new HashMap<>();
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(ids);
        new RestTemplate().exchange(returnUrl, HttpMethod.POST, requestEntity, String.class, parameters);
    }
}
