package pers.xiaoming.fault_tolerance.hystrix.basic_func_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.common.test.TestConstants;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripObservableController;

@ActiveProfiles("hystrix-basic-func-test")
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

    @Test
    public void testAsyncGet() throws Exception {
        TripInfo info = myTripController.getAsync(TestConstants.DEMO_TRIP_ID);
        Assert.assertEquals(info, TestConstants.DEMO_TRIP_INFO);
    }

    @Test
    public void testGetFromHotObservable() throws Exception {
        TripInfo info = myTripController.getFromHotObservable(TestConstants.DEMO_TRIP_ID);
        Assert.assertEquals(info, TestConstants.DEMO_TRIP_INFO);
    }

    @Test
    public void testGetFromColdObservable() throws Exception {
        TripInfo info = myTripController.getFromColdObservable(TestConstants.DEMO_TRIP_ID);
        Assert.assertEquals(info, TestConstants.DEMO_TRIP_INFO);
    }

    @Autowired
    private MyTripObservableController myTripObservableController;

    @Test
    public void testObservableControllerHotGet() throws Exception {
        TripInfo info = myTripObservableController.getFromHotObservable(TestConstants.DEMO_TRIP_ID);
        Assert.assertEquals(info, TestConstants.DEMO_TRIP_INFO);
    }

    @Test
    public void testObservableControllerColdGet() throws Exception {
        TripInfo info = myTripObservableController.getFromColdObservable(TestConstants.DEMO_TRIP_ID);
        Assert.assertEquals(info, TestConstants.DEMO_TRIP_INFO);
    }
}
