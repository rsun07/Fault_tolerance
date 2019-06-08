package pers.xiaoming.fault_tolerance.common.test;

import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;
import java.util.Random;

public class CircuitBreakerTestHotelHttpClient implements HttpClient {
    private final int errorThreshold;

    private Random random = new Random(100);

    public CircuitBreakerTestHotelHttpClient(int errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    @Override
    public String get(long id) throws IOException {
        if (random.nextInt(100) < errorThreshold) {
            return TestConstants.DEMO_HOTEL_INFO_STRING;
        } else {
            throw new IOException("Backend not available");
        }
    }
}
