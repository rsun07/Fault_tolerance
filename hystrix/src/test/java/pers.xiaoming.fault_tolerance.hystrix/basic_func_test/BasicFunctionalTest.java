package pers.xiaoming.fault_tolerance.hystrix.basic_func_test;

import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripObservableController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@ActiveProfiles("basic-func-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BasicFunctionalTest {
    private static final HotelInfo DEFAULT_HOTEL_INFO = HotelInfo.builder()
            .refNum(101)
            .name("Hyatt")
            .checkinDate(LocalDate.now().toString())
            .checkoutDate(LocalDate.now().plusDays(1).toString())
            .build();

    private static AirlineInfo DEFAULT_AIRLINE_INFO = AirlineInfo.builder()
            .refNum(101)
            .name("AA")
            .departureAirportName("IAD")
            .arrivalAirportName("LAX")
            .departureTime(LocalDateTime.now().toString())
            .build();

    @Getter
    private static final int DEFAULT_TRIP_ID = 888;

    @Getter
    private static final TripInfo DEFAULT_TRIP_INFO = TripInfo.builder()
            .id(DEFAULT_TRIP_ID)
            .hotelInfo(DEFAULT_HOTEL_INFO)
            .airlineInfo(DEFAULT_AIRLINE_INFO)
            .build();

    @Autowired
    private MyTripController myTripController;

    @Test
    public void testSyncGet() throws IOException {
        TripInfo info = myTripController.get(DEFAULT_TRIP_ID);
        Assert.assertEquals(info, DEFAULT_TRIP_INFO);
    }

    @Test
    public void testAsyncGet() throws IOException, ExecutionException, InterruptedException {
        TripInfo info = myTripController.getAsync(DEFAULT_TRIP_ID);
        Assert.assertEquals(info, DEFAULT_TRIP_INFO);
    }

    @Test
    public void testHotObservable() throws IOException {
        TripInfo info = myTripController.getFromHotObservable(DEFAULT_TRIP_ID);
        Assert.assertEquals(info, DEFAULT_TRIP_INFO);
    }

    @Test
    public void testColdObservable() throws IOException {
        TripInfo info = myTripController.getFromColdObservable(DEFAULT_TRIP_ID);
        Assert.assertEquals(info, DEFAULT_TRIP_INFO);
    }

    @Autowired
    private MyTripObservableController myTripObservableController;

    @Test
    public void testHystrixObservableCommand() throws IOException {
        TripInfo info = myTripObservableController.getFromColdObservable(DEFAULT_TRIP_ID);
        Assert.assertEquals(info, DEFAULT_TRIP_INFO);

        info = myTripObservableController.getFromHotObservable(DEFAULT_TRIP_ID);
        Assert.assertEquals(info, DEFAULT_TRIP_INFO);
    }
}
