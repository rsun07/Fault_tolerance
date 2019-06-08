package pers.xiaoming.fault_tolerance.common.test;

import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;

public class FallbackTestHotelHttpClient implements HttpClient {
    @Override
    public String get(long id) throws IOException {
        throw new IOException("Backend not available");
    }
}
