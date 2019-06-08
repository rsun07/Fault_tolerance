package pers.xiaoming.fault_tolerance.resilience4j.basic_func_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.common.test.TestConstants;
import pers.xiaoming.fault_tolerance.resilience4j.Application;
import pers.xiaoming.fault_tolerance.resilience4j.controller.MyTripController;

@ActiveProfiles("rs4j-basic-func-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BasicFunctionalTest {
    @Autowired
    private MyTripController myTripController;

    @Test
    public void testSyncGet() throws Exception {
        TripInfo info = myTripController.get(TestConstants.DEMO_TRIP_ID);
        Assert.assertEquals(info, TestConstants.DEMO_TRIP_INFO);
    }
}
