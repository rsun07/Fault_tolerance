package pers.xiaoming.fault_tolerance.common.test;

import lombok.extern.slf4j.Slf4j;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;

@Slf4j
public class AsyncTestAirlineHttpClient implements HttpClient {
    private final int sleepPeriodInMillis;

    public AsyncTestAirlineHttpClient(int sleepPeriodInMillis) {
        this.sleepPeriodInMillis = sleepPeriodInMillis;
    }

    @Override
    public String get(long id) throws IOException {
        log.info("Start http call to Airline Client");
        try {
            Thread.sleep(sleepPeriodInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Finish http call to Airline Client");
        return TestConstants.DEMO_AIRLINE_INFO_STRING;
    }
}
