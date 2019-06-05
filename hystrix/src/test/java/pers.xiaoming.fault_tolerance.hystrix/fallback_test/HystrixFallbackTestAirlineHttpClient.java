package pers.xiaoming.fault_tolerance.hystrix.fallback_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.entity.AirlineInfo;

import java.io.IOException;
import java.time.LocalDateTime;

public class HystrixFallbackTestAirlineHttpClient implements HttpClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static AirlineInfo defaultAirlineInfo = AirlineInfo.builder()
            .refNum(101)
            .name("AA")
            .departureAirportName("IAD")
            .arrivalAirportName("LAX")
            .departureTime(LocalDateTime.now().toString())
            .build();


    @Override
    public String get(long id) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(defaultAirlineInfo);
    }
}
