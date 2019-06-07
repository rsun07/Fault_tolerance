package pers.xiaoming.fault_tolerance.resilience4j.basic_func_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;

@Profile("hystrx-basic-func-test")
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
