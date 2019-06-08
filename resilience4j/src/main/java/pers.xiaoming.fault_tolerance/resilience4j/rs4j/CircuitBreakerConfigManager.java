package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CircuitBreakerConfigManager {
    private static final int DEFAULT_FAILURE_RATE_THRESHOLD_PERCENTAGE = 35;
    private static final int DEFAULT_WAIT_DURING_OPEN_STATE_IN_MILLISECONDS = 10000;
    private static final int DEFAULT_RING_BUFFER_SIZE_IN_HALF_OPEN_STATE = 10;
    private static final int DEFAULT_RING_BUFFER_SIZE_IN_CLOSED_STATE = 10;

    private int failureThresholdPercentage;
    private int waitDuringOpenStateInMilliseconds;
    private int ringBufferSizeInHalfOpenState;
    private int ringBufferSizeInClosedState;

    private int fallbackEnabled;

    public CircuitBreakerConfigManager fillWithDefaults() {
        CircuitBreakerConfigManagerBuilder configsWithDefaultsBuilder = CircuitBreakerConfigManager.builder();

        if (failureThresholdPercentage > 0) {
            configsWithDefaultsBuilder.failureThresholdPercentage(failureThresholdPercentage);
        } else {
            configsWithDefaultsBuilder.failureThresholdPercentage(DEFAULT_FAILURE_RATE_THRESHOLD_PERCENTAGE);
        }

        if (waitDuringOpenStateInMilliseconds > 0) {
            configsWithDefaultsBuilder.waitDuringOpenStateInMilliseconds(waitDuringOpenStateInMilliseconds);
        } else {
            configsWithDefaultsBuilder.waitDuringOpenStateInMilliseconds(DEFAULT_WAIT_DURING_OPEN_STATE_IN_MILLISECONDS);
        }

        if (ringBufferSizeInHalfOpenState > 0) {
            configsWithDefaultsBuilder.ringBufferSizeInHalfOpenState(ringBufferSizeInHalfOpenState);
        } else {
            configsWithDefaultsBuilder.ringBufferSizeInHalfOpenState(DEFAULT_RING_BUFFER_SIZE_IN_HALF_OPEN_STATE);
        }

        if (ringBufferSizeInClosedState > 0) {
            configsWithDefaultsBuilder.ringBufferSizeInClosedState(ringBufferSizeInClosedState);
        } else {
            configsWithDefaultsBuilder.ringBufferSizeInClosedState(DEFAULT_RING_BUFFER_SIZE_IN_CLOSED_STATE);
        }

        return configsWithDefaultsBuilder.build();
    }
}
