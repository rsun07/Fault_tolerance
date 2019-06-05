package pers.xiaoming.fault_tolerance.hystrix.circuit_breaker_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;

import java.io.IOException;
import java.util.Random;

public class HystrixCircuitBreakerTestHotelHttpClient implements HttpClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final HotelInfo DEFAULT_HOTEL_INFO = new HotelInfo();

    private Random random = new Random(100);

    @Override
    public String get(long id) throws IOException {
        if (random.nextInt(100) < HystrixCircuitBreakerTestConfig.LOWER_ERROR_THRESHOLD_FOR_TEST) {
            return OBJECT_MAPPER.writeValueAsString(DEFAULT_HOTEL_INFO);
        } else {
            throw new IOException("Backend not available");
        }
    }
}
