package pers.xiaoming.fault_tolerance.common.test.client;

import lombok.Setter;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;

import java.io.IOException;

public class CircuitBreakerTestHotelHttpClient implements HttpClient {
    private final int TOTAL_CALLS;

    @Setter
    private int i;

    public CircuitBreakerTestHotelHttpClient(int totalCalls) {
        this.TOTAL_CALLS = totalCalls;
    }

    /**
     *
     * Mock an incident situation
     *
     * 1-20% calls success
     * 21%-60% calls failed
     * 61-100% calls success
     *
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public String get(long id) throws IOException {
        if (i <= TOTAL_CALLS * 0.2 || i > TOTAL_CALLS * 0.6) {
            return TestConstants.DEMO_HOTEL_INFO_STRING;
        } else {
            throw new IOException("Backend Not Available");
        }
    }
}
