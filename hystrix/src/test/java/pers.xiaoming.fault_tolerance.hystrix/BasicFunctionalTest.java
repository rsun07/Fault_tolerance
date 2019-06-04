package pers.xiaoming.fault_tolerance.hystrix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;
import pers.xiaoming.fault_tolerance.hystrix.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.service.HotelService;

import java.io.IOException;

@ActiveProfiles("basic-func-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BasicFunctionalTest {
    @Autowired
    private MyTripController myTripController;

    @Autowired
    private HotelService hotelService;

    @Test
    public void testControllerFunction() throws IOException {
        TripInfo info = myTripController.get(111);
        System.out.println(info);
    }
}
