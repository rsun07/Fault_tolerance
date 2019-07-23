package pers.xiaoming.fault_tolerance.rs4j_annotation.fallback_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.FallbackTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.client.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.rs4j_annotation.service.HotelService;

@Profile("rs4j-annotation-fallback-test")
@Configuration
public class Rs4jFallbackTestConfig {
    private static final int LOWER_ERROR_THRESHOLD_FOR_TEST = 5;

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

    @Primary
    @Bean
    @Qualifier("hotelService")
    public HotelService getTestHotelService(@Qualifier("hotelClient") HttpClient client) {
        return new Rs4jTestHotelService(client);
    }
}
