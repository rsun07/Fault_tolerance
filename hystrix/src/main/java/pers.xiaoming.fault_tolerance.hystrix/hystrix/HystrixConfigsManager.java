package pers.xiaoming.fault_tolerance.hystrix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
public class HystrixConfigsManager<T> {
    private static final int DEFAULT_CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE = 35;
    private static final int DEFAULT_CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD = 10;
    private static final int DEFAULT_CIRCUIT_BREAKER_WINDOW_IN_MILLISECONDS = 10000;

    private static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 100;

    private static final int DEFAULT_SEMAPHORE_MAX_CONCURRENT_REQUESTS = 512;

    private static final int DEFAULT_THREAD_POOL_CORE_SIZE = 8;
    private static final int DEFAULT_THREAD_POOL_QUEUE_SIZE = 64;
    private static final int DEFAULT_THREAD_KEEP_ALIVE_TIME_MINUTES = 5;

    private int circuitBreakerErrorThresholdPercentage = DEFAULT_CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE;
    private int circuitBreakerRequestVolumeThreshold = DEFAULT_CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD;
    private int circuitBreakerSleepWindowInMillis = DEFAULT_CIRCUIT_BREAKER_WINDOW_IN_MILLISECONDS;

    private int timeoutInMillis = DEFAULT_TIMEOUT_IN_MILLISECONDS;

    private int semaphoreMaxConcurrentRequests = DEFAULT_SEMAPHORE_MAX_CONCURRENT_REQUESTS;

    private HystrixCommandProperties.ExecutionIsolationStrategy isolationStrategy =
            HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;

    private int threadPoolCoreSize = DEFAULT_THREAD_POOL_CORE_SIZE;
    private int threadPoolMaxSize = DEFAULT_THREAD_POOL_CORE_SIZE * 2;
    private int threadPoolQueueSize = DEFAULT_THREAD_POOL_QUEUE_SIZE;
    private int threadKeepAliveTimeInMinutes = DEFAULT_THREAD_KEEP_ALIVE_TIME_MINUTES;

    private boolean enableFallback = false;

    @Getter
    private T fallback = null;

    private HystrixConfigsManager() {
    }

    public static HystrixConfigsManager ofDefaults() {
        return new HystrixConfigsManager();
    }

    public HystrixCommand.Setter getHystrixCommandSetter(String groupKey, String commandKey) {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerErrorThresholdPercentage(circuitBreakerErrorThresholdPercentage)
                        .withCircuitBreakerRequestVolumeThreshold(circuitBreakerRequestVolumeThreshold)
                        .withCircuitBreakerSleepWindowInMilliseconds(circuitBreakerSleepWindowInMillis)
                        .withExecutionTimeoutInMilliseconds(timeoutInMillis)
                        .withMetricsHealthSnapshotIntervalInMilliseconds(circuitBreakerSleepWindowInMillis)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(semaphoreMaxConcurrentRequests)
                        .withFallbackEnabled(enableFallback));

        if (isolationStrategy.equals(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)) {
            setter.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                    .withCoreSize(threadPoolCoreSize)
                    .withMaxQueueSize(threadPoolMaxSize)
                    .withQueueSizeRejectionThreshold(threadPoolMaxSize)
                    .withKeepAliveTimeMinutes(threadKeepAliveTimeInMinutes));
        }
        return setter;
    }

    public HystrixObservableCommand.Setter getHystrixObservableCommandSetter(String groupKey, String commandKey) {
        return HystrixObservableCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerErrorThresholdPercentage(circuitBreakerErrorThresholdPercentage)
                        .withCircuitBreakerRequestVolumeThreshold(circuitBreakerRequestVolumeThreshold)
                        .withCircuitBreakerSleepWindowInMilliseconds(circuitBreakerSleepWindowInMillis)
                        .withExecutionTimeoutInMilliseconds(timeoutInMillis)
                        .withMetricsHealthSnapshotIntervalInMilliseconds(circuitBreakerSleepWindowInMillis)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(semaphoreMaxConcurrentRequests)
                        .withFallbackEnabled(enableFallback)
                );
    }
}
