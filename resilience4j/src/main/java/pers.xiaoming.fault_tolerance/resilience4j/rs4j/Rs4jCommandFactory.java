package pers.xiaoming.fault_tolerance.resilience4j.rs4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import pers.xiaoming.fault_tolerance.common.backends.HttpGetInterface;

@Slf4j
public class Rs4jCommandFactory<T> {
    private final CircuitBreaker circuitBreaker;
    private final T fallback;

    public Rs4jCommandFactory(String name,
                              CircuitBreakerConfigManager<T> circuitBreakerConfigManager) {
        this.fallback = circuitBreakerConfigManager.getFallback();

        this.circuitBreaker = CircuitBreaker.of(name,
                circuitBreakerConfigManager.getCircuitBreakerConfig());
    }

    public T execute(HttpGetInterface<T> httpGetCall) {
        Try<T> result = Try.of(
                CircuitBreaker.decorateCheckedSupplier(
                        circuitBreaker,
                        httpGetCall::get
                ));

        if (fallback == null) {
            return result.get();
        } else {
            return result.recover(throwable -> {
                log.error(throwable.getMessage());
                return fallback;
            }).get();
        }
    }
}
