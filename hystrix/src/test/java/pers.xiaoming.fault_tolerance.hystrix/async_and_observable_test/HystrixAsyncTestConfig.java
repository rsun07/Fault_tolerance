package pers.xiaoming.fault_tolerance.hystrix.async_and_observable_test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixObservableCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixOptionalConfigs;

@Profile("hystrix-async-test")
@Configuration
public class HystrixAsyncTestConfig {
    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new HystrixAsyncTestHotelHttpClient();
    }

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new HystrixAsyncTestAirlineHttpClient();
    }

    private HystrixOptionalConfigs hystrixOptionalConfigs = HystrixOptionalConfigs.builder()
            .timeoutInMillis(HystrixAsyncTest.DEFAULT_SLEEP_TIME_IN_MILLIS * 3)
            .build();

    @Primary
    @Bean
    @Qualifier("hotelHystrixCommandFactory")
    public HystrixCommandFactory getMockHotelHystrixCommandFactory() {
        return new HystrixCommandFactory("Hotel", "Get_Hotel_Info", hystrixOptionalConfigs);
    }

    @Primary
    @Bean
    @Qualifier("airlineHystrixCommandFactory")
    public HystrixCommandFactory getMockAirlineHystrixCommandFactory() {
        return new HystrixCommandFactory("Airline", "Get_Airline_Info", hystrixOptionalConfigs);
    }

    @Primary
    @Bean
    @Qualifier("hotelHystrixObservableCommandFactory")
    public HystrixObservableCommandFactory getMockHotelHystrixObservableCommandFactory() {
        return new HystrixObservableCommandFactory("Hotel", "Get_Hotel_Info_Observable", hystrixOptionalConfigs);
    }

    @Primary
    @Bean
    @Qualifier("airlineHystrixObservableCommandFactory")
    public HystrixObservableCommandFactory getMockAirlineHystrixObservableCommandFactory() {
        return new HystrixObservableCommandFactory("Airline", "Get_Airline_Info_Observable", hystrixOptionalConfigs);
    }
}
