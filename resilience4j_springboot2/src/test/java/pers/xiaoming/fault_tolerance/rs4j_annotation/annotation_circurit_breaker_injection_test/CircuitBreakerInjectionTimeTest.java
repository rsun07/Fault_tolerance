package pers.xiaoming.fault_tolerance.rs4j_annotation.annotation_circurit_breaker_injection_test;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.rs4j_annotation.Application;
import pers.xiaoming.fault_tolerance.rs4j_annotation.controller.MyTripController;

@ActiveProfiles("rs4j-annotation-sprint-profile-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class CircuitBreakerInjectionTimeTest {
    @Autowired
    private MyTripController controller;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private CircuitBreakerRegistry registry;

    @Test
    public void test() {
        //appContext.getBean("hotel");
    }
}
