package ru.yvzhelnin.otus.hwauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yvzhelnin.otus.hwauth.dto.HealthInfoDto;
import ru.yvzhelnin.otus.hwauth.dto.VersionDto;

@RestController
@RequestMapping("/auth-app/info")
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
        return new VersionDto("0.1");
    }
}
