package pers.xiaoming.fault_tolerance.resilience4j.fallback_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.test.executor.FallbackTestExecutor;
import pers.xiaoming.fault_tolerance.resilience4j.Application;
import pers.xiaoming.fault_tolerance.resilience4j.controller.MyTripController;

@ActiveProfiles("rs4j-fallback-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Rs4jFallbackTest {

    @Autowired
    private MyTripController myTripController;

    // If there is fallback available, it will be returned every time when backend error happens
    // Even though circuit breaker not open, fallback will be returned
    @Test
    public void testFallback() {
        FallbackTestExecutor.execute(myTripController::get);
    }
}
