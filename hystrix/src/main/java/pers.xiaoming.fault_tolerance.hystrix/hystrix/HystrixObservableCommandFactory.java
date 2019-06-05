package pers.xiaoming.fault_tolerance.hystrix.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import rx.Observable;

public class HystrixObservableCommandFactory {
    private final HystrixObservableCommand.Setter hystrixObservableCommandSetter;

    private final String fallback;

    public HystrixObservableCommandFactory(String groupKey, String commandKey, HystrixOptionalConfigs configs) {
        if (configs.isEnableFallback()) {
            this.fallback = configs.getFallback();
        } else {
            this.fallback = null;
        }

        configs = configs.fillWithDefaults();
        this.hystrixObservableCommandSetter = HystrixObservableCommand.Setter
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
    }

    public HystrixObservableCommand<String> createCommand(HttpClient client, long id) {
        return new HystrixObservableCommand<String>(hystrixObservableCommandSetter) {
            @Override
            protected Observable<String> construct() {
                return Observable.fromCallable(() -> client.get(id));
            }

            @Override
            protected Observable<String> resumeWithFallback() {
                return Observable.just(fallback);
            }
        };
    }
}
