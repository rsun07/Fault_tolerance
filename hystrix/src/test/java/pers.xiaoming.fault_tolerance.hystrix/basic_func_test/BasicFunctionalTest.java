package pers.xiaoming.fault_tolerance.hystrix.basic_func_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;
import pers.xiaoming.fault_tolerance.hystrix.entity.TripInfo;

import java.io.IOException;

@ActiveProfiles("basic-func-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BasicFunctionalTest {

    @Autowired
    private MyTripController myTripController;

    @Test
    public void testControllerFunction() throws IOException {
        TripInfo info = myTripController.get(BasicFunctionalTestConfig.getDefaultTripId());
        Assert.assertEquals(info, BasicFunctionalTestConfig.getDefaultTripInfo());
    }
}
