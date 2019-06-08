package pers.xiaoming.fault_tolerance.resilience4j.circuit_breaker_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.common.test.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.CircuitBreakerConfigManager;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.Rs4jCommandFactory;

@Profile("rs4j-circuit-breaker-test")
@Configuration
public class HystrixCircuitBreakerTestConfig {
    static final int LOWER_ERROR_THRESHOLD_FOR_TEST = 5;

    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new CircuitBreakerTestHotelHttpClient(LOWER_ERROR_THRESHOLD_FOR_TEST);
    }

    @Primary
    @Bean
    @Qualifier("hotelRs4jCommandFactory")
    public Rs4jCommandFactory getMockHotelHystrixCommandFactory() {
        CircuitBreakerConfigManager configs = CircuitBreakerConfigManager.builder()
                .failureThresholdPercentage(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .waitDuringOpenStateInMilliseconds(10000)
                .ringBufferSizeInClosedState(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .ringBufferSizeInHalfOpenState(1)
                .build();
        return new Rs4jCommandFactory("Get_Hotel_Info", configs);
    }

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new TestAirlineDefaultValueHttpClient();
    }
}
