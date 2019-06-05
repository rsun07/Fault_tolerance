package pers.xiaoming.fault_tolerance.hystrix.async_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class HystrixAsyncTestAirlineHttpClient implements HttpClient {
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
        log.info("Start http call to Airline Client");
        HystrixAsyncTest.sleep();
        log.info("Finish http call to Airline Client");
        return OBJECT_MAPPER.writeValueAsString(DEFAULT_AIRLINE_INFO);
    }
}
