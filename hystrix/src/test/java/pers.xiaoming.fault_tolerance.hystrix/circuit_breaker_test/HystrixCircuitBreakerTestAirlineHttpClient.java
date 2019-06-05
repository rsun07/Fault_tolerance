package pers.xiaoming.fault_tolerance.hystrix.circuit_breaker_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;

import java.io.IOException;
import java.time.LocalDateTime;

public class HystrixCircuitBreakerTestAirlineHttpClient implements HttpClient {
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
