package ru.yvzhelnin.otus.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.order.dto.HealthInfoDto;
import ru.yvzhelnin.otus.order.dto.VersionDto;

@RestController
@RequestMapping("/info")
public class AppInfoController {

    @GetMapping("/health")
    public HealthInfoDto getHealth() {
        return new HealthInfoDto("OK");
    }

    @GetMapping("/status")
    public HealthInfoDto getStatus() {
        return new HealthInfoDto("OK");
    }

    @GetMapping("/version")
    public VersionDto getVersion() {
        return new VersionDto("1.0");
    }
}
