package pers.xiaoming.fault_tolerance.hystrix.fallback_test;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;

import java.io.IOException;
import java.time.LocalDate;

@ActiveProfiles("hystrix-fallback-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class HystrixFallbackTest {
    static final HotelInfo FALLBACK_HOTEL_INFO = HotelInfo.builder()
            .refNum(888)
            .name("Hilton")
            .checkinDate(LocalDate.now().toString())
            .checkoutDate(LocalDate.now().plusDays(2).toString())
            .build();

    @Autowired
    private MyTripController myTripController;

    // If there is fallback available, it will be returned every time when backend error happens
    // Even though circuit breaker not open, fallback will be returned
    @Test
    public void testFallback() throws IOException {
        TripInfo fallbackTripInfo = null;
        while(true) {
            try {
                fallbackTripInfo = myTripController.get(111);
            } catch (HystrixRuntimeException e) {
                // no log will be printed
                log.info(e.getMessage());
            }

            if (fallbackTripInfo != null) {
                break;
            }
        }
        System.out.println(fallbackTripInfo);
    }
}
