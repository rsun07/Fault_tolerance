package pers.xiaoming.fault_tolerance.hystrix.hystrix_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.entity.HotelInfo;

import java.io.IOException;
import java.util.Random;

public class HystrixTestHotelHttpClient implements HttpClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final HotelInfo DEFAULT_HOTEL_INFO = new HotelInfo();

    private Random random = new Random(100);

    @Override
    public String get(long id) throws IOException {
        if (random.nextInt(100) < HystrixTestConfig.LOWER_ERROR_THRESHOLD_FOR_TEST) {
            return OBJECT_MAPPER.writeValueAsString(DEFAULT_HOTEL_INFO);
        } else {
            throw new IOException("Backend not available");
        }
    }
}
