package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import com.netflix.hystrix.HystrixCommandProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HystrixOptionalConfigs {
    private static final int DEFAULT_CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE = 35;
    private static final int DEFAULT_CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD = 10;
    private static final int DEFAULT_CIRCUIT_BREAKER_WINDOW_IN_MILLISECONDS = 10000;

    private static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 100;

    private static final int DEFAULT_SEMAPHORE_MAX_CONCURRENT_REQUESTS = 512;

    private static final int DEFAULT_THREAD_POOL_CORE_SIZE = 8;
    private static final int DEFAULT_THREAD_POOL_QUEUE_SIZE = 64;
    private static final int DEFAULT_THREAD_KEEP_ALIVE_TIME_MINUTES = 5;

    private int circuitBreakerErrorThresholdPercentage;
    private int circuitBreakerRequestVolumeThreshold;
    private int circuitBreakerSleepWindowInMillis;

    private int semaphoreMaxConcurrentRequests;

    private int threadPoolCoreSize;
    private int threadPoolQueueSize;
    private int threadKeepAliveTimeInMinutes;

    private int timeoutInMillis;

    private boolean enableFallback;
    private String fallback;

    private HystrixCommandProperties.ExecutionIsolationStrategy isolationStrategy;

    public HystrixOptionalConfigs fillWithDefaults() {
        HystrixOptionalConfigsBuilder configsWithDefaultsBuilder = HystrixOptionalConfigs.builder();

        if (circuitBreakerErrorThresholdPercentage > 0) {
            configsWithDefaultsBuilder.circuitBreakerErrorThresholdPercentage(circuitBreakerErrorThresholdPercentage);
        } else {
            configsWithDefaultsBuilder.circuitBreakerErrorThresholdPercentage(DEFAULT_CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE);
        }

        if (circuitBreakerRequestVolumeThreshold > 0) {
            configsWithDefaultsBuilder.circuitBreakerRequestVolumeThreshold(circuitBreakerRequestVolumeThreshold);
        } else {
            configsWithDefaultsBuilder.circuitBreakerRequestVolumeThreshold(DEFAULT_CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD);
        }

        if (circuitBreakerSleepWindowInMillis > 0) {
            configsWithDefaultsBuilder.circuitBreakerSleepWindowInMillis(circuitBreakerSleepWindowInMillis);
        } else {
            configsWithDefaultsBuilder.circuitBreakerSleepWindowInMillis(DEFAULT_CIRCUIT_BREAKER_WINDOW_IN_MILLISECONDS);
        }

        if (timeoutInMillis > 0) {
            configsWithDefaultsBuilder.timeoutInMillis(timeoutInMillis);
        } else {
            configsWithDefaultsBuilder.timeoutInMillis(DEFAULT_TIMEOUT_IN_MILLISECONDS);
        }

        if (isolationStrategy != null) {
            configsWithDefaultsBuilder.isolationStrategy(isolationStrategy);
        } else {
            configsWithDefaultsBuilder.isolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);
        }

        if (configsWithDefaultsBuilder.isolationStrategy.equals(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)) {
            if (semaphoreMaxConcurrentRequests > 0) {
                configsWithDefaultsBuilder.semaphoreMaxConcurrentRequests(semaphoreMaxConcurrentRequests);
            } else {
                configsWithDefaultsBuilder.semaphoreMaxConcurrentRequests(DEFAULT_SEMAPHORE_MAX_CONCURRENT_REQUESTS);
            }
        } else {
            if (threadPoolCoreSize > 0) {
                configsWithDefaultsBuilder.threadPoolCoreSize(threadPoolCoreSize);
            } else {
                configsWithDefaultsBuilder.threadPoolCoreSize(DEFAULT_THREAD_POOL_CORE_SIZE);
            }

            if (threadPoolQueueSize > 0) {
                configsWithDefaultsBuilder.threadPoolQueueSize(threadPoolQueueSize);
            } else {
                configsWithDefaultsBuilder.threadPoolQueueSize(DEFAULT_THREAD_POOL_QUEUE_SIZE);
            }

            if (threadKeepAliveTimeInMinutes > 0) {
                configsWithDefaultsBuilder.threadKeepAliveTimeInMinutes(threadKeepAliveTimeInMinutes);
            } else {
                configsWithDefaultsBuilder.threadKeepAliveTimeInMinutes(DEFAULT_THREAD_KEEP_ALIVE_TIME_MINUTES);
            }
        }

        if (enableFallback) {
            configsWithDefaultsBuilder.enableFallback(enableFallback);
            configsWithDefaultsBuilder.fallback(fallback);
        }

        return configsWithDefaultsBuilder.build();
    }
}
