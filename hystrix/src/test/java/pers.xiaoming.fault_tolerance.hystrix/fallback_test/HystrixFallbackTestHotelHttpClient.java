package pers.xiaoming.fault_tolerance.hystrix.fallback_test;

import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;

import java.io.IOException;

public class HystrixFallbackTestHotelHttpClient implements HttpClient {
    @Override
    public String get(long id) throws IOException {
        throw new IOException("Backend not available");
    }
}
