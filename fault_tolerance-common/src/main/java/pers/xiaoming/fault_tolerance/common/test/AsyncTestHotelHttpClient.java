package pers.xiaoming.fault_tolerance.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class AsyncTestHotelHttpClient implements HttpClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final HotelInfo DEFAULT_HOTEL_INFO = HotelInfo.builder()
            .refNum(888)
            .name("Hilton")
            .checkinDate(LocalDate.now().toString())
            .checkoutDate(LocalDate.now().plusDays(2).toString())
            .build();

    private final int sleepPeriodInMillis;

    public AsyncTestHotelHttpClient(int sleepPeriodInMillis) {
        this.sleepPeriodInMillis = sleepPeriodInMillis;
    }

    @Override
    public String get(long id) throws IOException {
        log.info("Start http call to Hotel Client");
        try {
            Thread.sleep(sleepPeriodInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Finish http call to Hotel Client");
        return OBJECT_MAPPER.writeValueAsString(DEFAULT_HOTEL_INFO);
    }
}
