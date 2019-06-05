package pers.xiaoming.fault_tolerance.hystrix.circuit_breaker_test;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;

import java.io.IOException;

@ActiveProfiles("hystrix-circuit-breaker-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class HystrixCircuitBreakerTest {
    private static final int TOTAL_ROUNDS = 100;
    private static final String SHORT_CICUIT_MSG = "Get_Hotel_Info short-circuited and no fallback available.";

    @Autowired
    private MyTripController myTripController;

    @Test
    public void testCircuitBreaker() throws IOException {
        int count = 0;
        String lastMessage = null;

        for (int i = 0; i < TOTAL_ROUNDS; i++) {
            try {
                myTripController.get(111);
            } catch (HystrixRuntimeException e) {
                log.info(e.getMessage());
                count++;

                if (i == TOTAL_ROUNDS - 1) {
                    lastMessage = e.getMessage();
                }
            }
        }

        Assert.assertEquals(SHORT_CICUIT_MSG, lastMessage);
        Assert.assertTrue(count >=
                TOTAL_ROUNDS * (1 - HystrixCircuitBreakerTestConfig.LOWER_ERROR_THRESHOLD_FOR_TEST));
    }
}
