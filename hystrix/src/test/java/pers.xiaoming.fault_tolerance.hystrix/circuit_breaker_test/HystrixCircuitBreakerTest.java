package pers.xiaoming.fault_tolerance.hystrix.circuit_breaker_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.test.client.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.executor.CircuitBreakerTestExecutor;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;

@ActiveProfiles("hystrix-circuit-breaker-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
@Ignore
public class HystrixCircuitBreakerTest {
    static final int TOTAL_ROUNDS = 100;

    private static final String SHORT_CIRCUIT_MSG = "Get_Hotel_Info short-circuited and fallback disabled.";

    @Autowired
    private MyTripController myTripController;

    @Autowired
    private CircuitBreakerTestHotelHttpClient httpClient;

    @Test
    public void testCircuitBreaker() throws InterruptedException {
        CircuitBreakerTestExecutor.execute(TOTAL_ROUNDS, SHORT_CIRCUIT_MSG, httpClient, myTripController::get);
    }
}
