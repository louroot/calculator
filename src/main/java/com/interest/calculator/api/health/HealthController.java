package com.interest.calculator.api.health;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HealthController {
    private final HealthService healthService;

    @GetMapping
    public ResponseEntity<HealthCheck> checkHealth() {
        HealthCheck dbCheck = healthService.dbCheck();
        return new ResponseEntity<>(dbCheck, dbCheck.getStatus().getHttpStatus());
    }
}
