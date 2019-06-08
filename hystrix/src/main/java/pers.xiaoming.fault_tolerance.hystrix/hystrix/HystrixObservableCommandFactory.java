package pers.xiaoming.fault_tolerance.hystrix.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import pers.xiaoming.fault_tolerance.common.backends.HttpGetInterface;
import rx.Observable;

public class HystrixObservableCommandFactory<T> {
    private final HystrixObservableCommand.Setter hystrixObservableCommandSetter;

    private final T fallback;

    public HystrixObservableCommandFactory(String groupKey, String commandKey, HystrixOptionalConfigs<T> configs) {
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

    public HystrixObservableCommand<T> createCommand(HttpGetInterface<T> httpGetFunc) {
        return new HystrixObservableCommand<T>(hystrixObservableCommandSetter) {
            @Override
            protected Observable<T> construct() {
                return Observable.fromCallable(httpGetFunc::get);
            }

            @Override
            protected Observable<T> resumeWithFallback() {
                return Observable.just(fallback);
            }
        };
    }
}
