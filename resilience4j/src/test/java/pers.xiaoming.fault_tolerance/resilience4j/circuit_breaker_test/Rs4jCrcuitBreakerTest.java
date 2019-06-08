package pers.xiaoming.fault_tolerance.resilience4j.circuit_breaker_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.resilience4j.Application;
import pers.xiaoming.fault_tolerance.resilience4j.controller.MyTripController;

@ActiveProfiles("rs4j-circuit-breaker-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
@Ignore("Test takes too long")
public class Rs4jCrcuitBreakerTest {
    private static final int TOTAL_ROUNDS = 100;
    private static final String SHORT_CICUIT_MSG = "CircuitBreaker 'Get_Hotel_Info' is OPEN and does not permit further calls";

    @Autowired
    private MyTripController myTripController;

    @Test
    public void testCircuitBreaker() {
        int count = 0;
        String lastMessage = null;

        for (int i = 0; i < TOTAL_ROUNDS; i++) {
            try {
                myTripController.get(111);
            } catch (Exception e) {
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
