package pers.xiaoming.fault_tolerance.hystrix.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;

public class HystrixCommandFactory {
    private final HystrixCommand.Setter hystrixCommandSetter;

    private final String fallback;

    public HystrixCommandFactory(String groupKey, String commandKey, HystrixOptionalConfigs configs) {
        if (configs.isEnableFallback()) {
            this.fallback = configs.getFallback();
        } else {
            this.fallback = null;
        }

        configs = configs.fillWithDefaults();
        this.hystrixCommandSetter = HystrixCommand.Setter
                // Hystrix uses the command group key to group together commands such as
                // for reporting, alerting, dashboards, or team/library ownership.
                // By default Hystrix uses this to define the command thread-pool unless a separate one is defined.
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerErrorThresholdPercentage(
                                configs.getCircuitBreakerErrorThresholdPercentage())
                        .withCircuitBreakerRequestVolumeThreshold(
                                configs.getCircuitBreakerRequestVolumeThreshold())
                        .withCircuitBreakerSleepWindowInMilliseconds(
                                configs.getCircuitBreakerSleepWindowInMillis())
                        .withExecutionTimeoutInMilliseconds(configs.getTimeoutInMillis())
                        .withMetricsHealthSnapshotIntervalInMilliseconds(
                                configs.getCircuitBreakerSleepWindowInMillis())
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(
                                configs.getSemaphoreMaxConcurrentRequests()
                        )
                        .withFallbackEnabled(configs.isEnableFallback())
                );

        if (configs.getIsolationStrategy().equals(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)) {
            hystrixCommandSetter.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                    .withCoreSize(configs.getThreadPoolCoreSize())
                    .withKeepAliveTimeMinutes(configs.getThreadKeepAliveTimeInMinutes())
                    .withMaxQueueSize(configs.getThreadPoolQueueSize())
                    .withQueueSizeRejectionThreshold(configs.getThreadPoolQueueSize())
            );
        }
    }

    public HystrixCommand<String> createCommand(HttpClient client, long id) {
        return new HystrixCommand<String>(hystrixCommandSetter) {
            @Override
            protected String run() throws IOException {
                return client.get(id);
            }

            @Override
            protected String getFallback() {
                return fallback;
            }
        };
    }
}
