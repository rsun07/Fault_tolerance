package pers.xiaoming.fault_tolerance.hystrix.circuit_breaker_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixConfigsManager;

@Profile("hystrix-circuit-breaker-test")
@Configuration
public class HystrixCircuitBreakerTestConfig {
    private static final int LOWER_ERROR_THRESHOLD_FOR_TEST = 5;

    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new CircuitBreakerTestHotelHttpClient(HystrixCircuitBreakerTest.TOTAL_ROUNDS);
    }

    @Primary
    @Bean
    @Qualifier("hotelHystrixCommandFactory")
    public HystrixCommandFactory<String> getMockHotelHystrixCommandFactory() {
        HystrixConfigsManager<String> configs = HystrixConfigsManager.<String>getBuilderWithDefaultValues()
                .circuitBreakerErrorThresholdPercentage(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .circuitBreakerRequestVolumeThreshold(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .circuitBreakerSleepWindowInMillis(50)
                .build();
        return new HystrixCommandFactory<>("Hotel", "Get_Hotel_Info", configs);
    }

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new TestAirlineDefaultValueHttpClient();
    }
}
