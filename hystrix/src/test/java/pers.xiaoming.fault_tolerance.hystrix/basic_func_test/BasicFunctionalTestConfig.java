package pers.xiaoming.fault_tolerance.hystrix.basic_func_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Profile("basic-func-test")
@Configuration
public class BasicFunctionalTestConfig {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Indicates that a bean should be given preference when multiple candidates
    // are qualified to autowire a single-valued dependency.
    @Primary
    @Bean
    @Qualifier("hotelClient")
    public HttpClient getMockHotelClient() {
        return new HttpClient() {
            @Override
            public String get(long id) throws IOException {
                return OBJECT_MAPPER.writeValueAsString(BasicFunctionalTest.getDEFAULT_TRIP_INFO().getHotelInfo());
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
                return OBJECT_MAPPER.writeValueAsString(BasicFunctionalTest.getDEFAULT_TRIP_INFO().getAirlineInfo());
            }
        };
    }
}
