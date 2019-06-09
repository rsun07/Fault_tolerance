package pers.xiaoming.fault_tolerance.resilience4j.fallback_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.FallbackTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.CircuitBreakerConfigManager;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.Rs4jCommandFactory;

@Profile("rs4j-fallback-test")
@Configuration
public class Rs4jFallbackTestConfig {
    private static final int LOWER_ERROR_THRESHOLD_FOR_TEST = 5;

    @Primary
    @Bean
    @Qualifier("hotelRs4jCommandFactory")
    public Rs4jCommandFactory<String> getMockHotelRs4jCommandFactory() throws JsonProcessingException {
        CircuitBreakerConfigManager<String> configs = CircuitBreakerConfigManager.<String>builder()
                .failureThresholdPercentage(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .waitDuringOpenStateInMilliseconds(10000)
                .ringBufferSizeInClosedState(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .ringBufferSizeInHalfOpenState(1)
                .fallbackEnabled(true)
                .fallback(TestConstants.FALLBACK_HOTEL_INFO_STRING)
                .build();
        return new Rs4jCommandFactory<>("Get_Hotel_Info", configs);
    }

    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new FallbackTestHotelHttpClient();
    }

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new TestAirlineDefaultValueHttpClient();
    }
}
