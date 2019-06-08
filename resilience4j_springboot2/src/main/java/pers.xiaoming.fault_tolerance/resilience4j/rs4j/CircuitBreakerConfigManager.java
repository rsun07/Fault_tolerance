package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * Include generic type fallback and overwrite default values of original configs
 *
 * @param <T>
 */
@Builder(toBuilder = true)
@AllArgsConstructor
public class CircuitBreakerConfigManager<T> {
    private static final int DEFAULT_FAILURE_RATE_THRESHOLD_PERCENTAGE = 35;
    private static final int DEFAULT_WAIT_DURING_OPEN_STATE_IN_MILLISECONDS = 10000;
    private static final int DEFAULT_RING_BUFFER_SIZE_IN_HALF_OPEN_STATE = 10;
    private static final int DEFAULT_RING_BUFFER_SIZE_IN_CLOSED_STATE = 10;

    private int failureThresholdPercentage = DEFAULT_FAILURE_RATE_THRESHOLD_PERCENTAGE;
    private int waitDuringOpenStateInMilliseconds = DEFAULT_WAIT_DURING_OPEN_STATE_IN_MILLISECONDS;
    private int ringBufferSizeInHalfOpenState = DEFAULT_RING_BUFFER_SIZE_IN_HALF_OPEN_STATE;
    private int ringBufferSizeInClosedState = DEFAULT_RING_BUFFER_SIZE_IN_HALF_OPEN_STATE;

    private boolean fallbackEnabled = false;

    @Getter
    private T fallback = null;

    private CircuitBreakerConfigManager() {
    }

    public static CircuitBreakerConfigManager ofDefaults() {
        return new CircuitBreakerConfigManager();
    }

    public static <T> CircuitBreakerConfigManagerBuilder getBuilderWithDefaultValues() {
        return new CircuitBreakerConfigManager<T>().toBuilder();
    }

    public CircuitBreakerConfig getCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(failureThresholdPercentage)
                .waitDurationInOpenState(Duration.ofMillis(waitDuringOpenStateInMilliseconds))
                .ringBufferSizeInHalfOpenState(ringBufferSizeInHalfOpenState)
                .ringBufferSizeInClosedState(ringBufferSizeInClosedState)
                .build();
    }
}
