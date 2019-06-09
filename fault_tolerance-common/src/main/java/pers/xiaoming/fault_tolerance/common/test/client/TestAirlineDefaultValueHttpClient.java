package pers.xiaoming.fault_tolerance.common.test.client;

import lombok.extern.slf4j.Slf4j;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;

import java.io.IOException;

@Slf4j
public class TestAirlineDefaultValueHttpClient implements HttpClient {
    @Override
    public String get(long id) throws IOException {
        return TestConstants.DEMO_AIRLINE_INFO_STRING;
    }
}
