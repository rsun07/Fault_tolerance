package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.time.Duration;

public class Rs4jCommandFactory {
    private final CircuitBreaker circuitBreaker;

    public Rs4jCommandFactory(String name, CircuitBreakerConfigManager circuitBreakerConfigManager) {
        circuitBreakerConfigManager = circuitBreakerConfigManager.fillWithDefaults();
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(circuitBreakerConfigManager.getFailureThresholdPercentage())
                .waitDurationInOpenState(Duration.ofMillis(circuitBreakerConfigManager.getWaitDuringOpenStateInMilliseconds()))
                .ringBufferSizeInHalfOpenState(circuitBreakerConfigManager.getRingBufferSizeInHalfOpenState())
                .ringBufferSizeInClosedState(circuitBreakerConfigManager.getRingBufferSizeInClosedState())
                .build();

        this.circuitBreaker = CircuitBreaker.of(name, circuitBreakerConfig)
    }

    public String execute(HttpClient client, long id) throws Exception {
        return circuitBreaker.executeCallable(() -> client.get(id));
    }
}
