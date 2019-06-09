package pers.xiaoming.fault_tolerance.rs4j_annotation.fallback_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;
import pers.xiaoming.fault_tolerance.rs4j_annotation.Application;
import pers.xiaoming.fault_tolerance.rs4j_annotation.controller.MyTripController;

import java.io.IOException;

@ActiveProfiles("rs4j-annotation-fallback-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Rs4jFallbackTest {

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
            } catch (Exception e) {
                // no log will be printed
                e.printStackTrace();
            }

            if (fallbackTripInfo != null) {
                break;
            }
        }
        Assert.assertEquals(fallbackTripInfo.getHotelInfo(), TestConstants.FALLBACK_HOTEL_INFO);
    }
}
