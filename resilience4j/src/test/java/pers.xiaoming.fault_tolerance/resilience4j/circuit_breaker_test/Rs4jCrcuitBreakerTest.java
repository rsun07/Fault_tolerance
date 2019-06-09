package pers.xiaoming.fault_tolerance.resilience4j.circuit_breaker_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.test.client.CircuitBreakerTestHotelHttpClient;
import pers.xiaoming.fault_tolerance.common.test.executor.CircuitBreakerExecutor;
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
        CircuitBreakerExecutor.execute(TOTAL_ROUNDS, SHORT_CIRCUIT_MSG, httpClient, myTripController::get);
    }
}
