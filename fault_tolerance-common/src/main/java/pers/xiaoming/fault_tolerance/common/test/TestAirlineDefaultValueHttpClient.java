package pers.xiaoming.fault_tolerance.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class TestAirlineDefaultValueHttpClient implements HttpClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final AirlineInfo DEFAULT_AIRLINE_INFO = AirlineInfo.builder()
            .refNum(101)
            .name("AA")
            .departureAirportName("IAD")
            .arrivalAirportName("LAX")
            .departureTime(LocalDateTime.now().toString())
            .build();


    @Override
    public String get(long id) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(DEFAULT_AIRLINE_INFO);
    }
}
