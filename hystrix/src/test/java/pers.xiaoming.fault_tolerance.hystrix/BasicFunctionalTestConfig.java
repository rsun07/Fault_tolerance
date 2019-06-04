package pers.xiaoming.fault_tolerance.hystrix;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.hystrix.entity.HotelInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Profile("basic-func-test")
@Configuration
public class BasicFunctionalTestConfig {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private HotelInfo defaultHotelInfo = HotelInfo.builder()
            .refNum(101)
            .name("Hyatt")
            .checkinDate(LocalDate.now().toString())
            .checkoutDate(LocalDate.now().plusDays(1).toString())
            .build();

    private AirlineInfo defaultAirlineInfo = AirlineInfo.builder()
            .refNum(101)
            .name("AA")
            .departureAirportName("IAD")
            .arrivalAirportName("LAX")
            .departureTime(LocalDateTime.now().toString())
            .build();

    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new HttpClient() {
            @Override
            public String get(long id) throws IOException {
                return OBJECT_MAPPER.writeValueAsString(defaultHotelInfo);
            }
        };
    }

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
