package pers.xiaoming.fault_tolerance.rs4j_annotation.circuit_breaker_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.TestAirlineDefaultValueHttpClient;

@Profile("rs4j-circuit-breaker-test")
@Configuration
public class Rs4jCircuitBreakerTestConfig {
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
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new TestAirlineDefaultValueHttpClient();
    }
}
