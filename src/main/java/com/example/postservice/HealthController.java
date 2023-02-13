package com.example.postservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/healthz")
public class HealthController {

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class HealthEntity {
        private int responseCode;
        private String version;
    }

    @GetMapping
    public ResponseEntity<HealthEntity> healthCheck() {
        HealthEntity healthEntity = new HealthEntity();
        healthEntity.setResponseCode(200);
        healthEntity.setVersion("1.0.0");
        return ResponseEntity.ok(healthEntity);
    }
}
