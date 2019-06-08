package pers.xiaoming.fault_tolerance.hystrix.fallback_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.FallbackTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.TestAirlineDefaultValueHttpClient;
import pers.xiaoming.fault_tolerance.common.test.TestConstants;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixConfigsManager;

@Profile("hystrix-fallback-test")
@Configuration
public class HystrixFallbackTestConfig {
    private static final int LOWER_ERROR_THRESHOLD_FOR_TEST = 5;

    @Primary
    @Bean
    @Qualifier("hotelHystrixCommandFactory")
    public HystrixCommandFactory<String> getMockHotelHystrixCommandFactory() throws JsonProcessingException {
        HystrixConfigsManager<String> configs = HystrixConfigsManager.<String>builder()
                .circuitBreakerErrorThresholdPercentage(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .circuitBreakerRequestVolumeThreshold(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .circuitBreakerSleepWindowInMillis(100)
                .enableFallback(true)
                .fallback(TestConstants.FALLBACK_HOTEL_INFO_STRING)
                .build();
        return new HystrixCommandFactory<>("Hotel", "Get_Hotel_Info_Test", configs);
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
