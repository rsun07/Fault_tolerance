package pers.xiaoming.fault_tolerance.hystrix.hystrix_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.hystrix.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.hystrix.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixOptionalConfigs;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Profile("hystrix-test")
@Configuration
public class HystrixTestConfig {
    static final int LOWER_ERROR_THRESHOLD_FOR_TEST = 5;

    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new HystrixTestHotelHttpClient();
    }

    @Primary
    @Bean
    @Qualifier("hotelHystrixCommandFactory")
    public HystrixCommandFactory getMockHotelHystrixCommandFactory() {
        HystrixOptionalConfigs configs = HystrixOptionalConfigs.builder()
                .circuitBreakerErrorThresholdPercentage(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .circuitBreakerRequestVolumeThreshold(LOWER_ERROR_THRESHOLD_FOR_TEST)
                .circuitBreakerSleepWindowInMillis(100)
                .build();
        return new HystrixCommandFactory("Hotel", "Get_Hotel_Info", configs);
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static AirlineInfo defaultAirlineInfo = AirlineInfo.builder()
            .refNum(101)
            .name("AA")
            .departureAirportName("IAD")
            .arrivalAirportName("LAX")
            .departureTime(LocalDateTime.now().toString())
            .build();

    @Primary
    @Bean
    @Qualifier("airlineClient")
    public HttpClient getMockAirlineClient() {
        return new HttpClient() {
            @Override
            public String get(long id) throws IOException {
                return OBJECT_MAPPER.writeValueAsString(defaultAirlineInfo);
            }
        };
    }
}
