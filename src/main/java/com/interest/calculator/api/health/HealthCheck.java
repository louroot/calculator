package com.interest.calculator.api.health;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class HealthCheck {
    private String name;
    private String description;
    private Status status;
}
