package pers.xiaoming.fault_tolerance.rs4j_annotation.circuit_breaker_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.rs4j_annotation.fallback_test.Rs4jTestHotelService;

@Profile("rs4j-annotation-circuit-breaker-test")
@Configuration
public class Rs4jCircuitBreakerTestConfig {
    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new CircuitBreakerTestHotelHttpClient(Rs4jCrcuitBreakerTest.TOTAL_ROUNDS);
    }

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new TestAirlineDefaultValueHttpClient();
    }
}
