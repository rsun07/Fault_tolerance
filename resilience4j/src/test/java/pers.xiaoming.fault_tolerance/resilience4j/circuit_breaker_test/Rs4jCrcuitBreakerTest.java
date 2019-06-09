package pers.xiaoming.fault_tolerance.resilience4j.circuit_breaker_test;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.common.test.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.TestConstants;
import pers.xiaoming.fault_tolerance.resilience4j.Application;
import pers.xiaoming.fault_tolerance.resilience4j.controller.MyTripController;

import java.io.IOException;

@ActiveProfiles("rs4j-circuit-breaker-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Rs4jCrcuitBreakerTest {
    static final int TOTAL_ROUNDS = 100;

    private static final String SHORT_CIRCUIT_MSG = "CircuitBreaker 'Get_Hotel_Info' is OPEN and does not permit further calls";

    @Autowired
    private MyTripController myTripController;

    @Autowired
    private CircuitBreakerTestHotelHttpClient httpClient;

    @Test
    public void testCircuitBreaker() throws InterruptedException, IOException {
        String lastMessage = null;
        boolean isCurcuitBreakerOpen = false;
        TripInfo tripInfo = null;

        for (int i = 0; i < TOTAL_ROUNDS; i++) {
            httpClient.setI(i);

            try {
                tripInfo = myTripController.get(TestConstants.DEMO_TRIP_ID);
                isCurcuitBreakerOpen = false;
            } catch (Exception e) {
                log.info(e.getMessage());

                tripInfo = null;

                lastMessage = e.getMessage();
                if (SHORT_CIRCUIT_MSG.equals(lastMessage)) {
                    isCurcuitBreakerOpen = true;
                }
            }

            // pers.xiaoming.fault_tolerance.common.test.CircuitBreakerTestHotelHttpClient
            // 0-20 should be success call
            // 21-60 should be failed call
            // 61-100 should be success call
            switch(i) {
                case 12:
                case 92:
                    log.info("Checking at i = {}", i);
                    Assert.assertSame(false, isCurcuitBreakerOpen);
                    Assert.assertEquals(tripInfo, TestConstants.DEMO_TRIP_INFO);
                    log.info("Check passed at i = {}", i);
                    break;
                case 52 :
                    log.info("Checking at i = {}", i);
                    Assert.assertSame(true, isCurcuitBreakerOpen);
                    Assert.assertEquals(SHORT_CIRCUIT_MSG, lastMessage);
                    log.info("Check passed at i = {}", i);
                    break;
            }

            Thread.sleep(10);
        }
    }
}
