package pers.xiaoming.fault_tolerance.hystrix.async_and_observable_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.xiaoming.fault_tolerance.hystrix.Application;
import pers.xiaoming.fault_tolerance.hystrix.controller.MyTripController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@ActiveProfiles("hystrix-async-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class HystrixAsyncTest {
    static final int DEFAULT_SLEEP_TIME_IN_MILLIS = 2000;

    @Autowired
    private MyTripController myTripController;

    /*
        2019-06-04 22:12:11.436  INFO 88769 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Start http call to Hotel Client
        2019-06-04 22:12:13.441  INFO 88769 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Finish http call to Hotel Client
        2019-06-04 22:12:13.485  INFO 88769 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Start http call to Airline Client
        2019-06-04 22:12:17.601  INFO 88769 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Finish http call to Airline Client
     */
    @Test
    public void testSyncCall() throws IOException {
        long startTime = System.currentTimeMillis();
        myTripController.get(123);
        long endTime = System.currentTimeMillis();

        // At least 2 times sleep time because other stuffs also take time
        Assert.assertTrue(endTime - startTime > DEFAULT_SLEEP_TIME_IN_MILLIS * 2);
        Assert.assertTrue(endTime - startTime < DEFAULT_SLEEP_TIME_IN_MILLIS * 3);
    }

    /*
        2019-06-04 22:13:44.702  INFO 88792 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Start http call to Hotel Client
        2019-06-04 22:13:44.702  INFO 88792 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Start http call to Airline Client
        2019-06-04 22:13:46.705  INFO 88792 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Finish http call to Hotel Client
        2019-06-04 22:13:46.705  INFO 88792 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Finish http call to Airline Client
     */
    @Test
    public void testAsyncCall() throws IOException, ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        myTripController.getAsync(123);
        long endTime = System.currentTimeMillis();

        Assert.assertTrue(endTime - startTime > DEFAULT_SLEEP_TIME_IN_MILLIS);
        Assert.assertTrue(endTime - startTime < DEFAULT_SLEEP_TIME_IN_MILLIS * 2);
    }

    /*
        2019-06-05 15:21:24.609  INFO 26734 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Start http call to Hotel Client
        2019-06-05 15:21:24.609  INFO 26734 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Start http call to Airline Client
        2019-06-05 15:21:26.615  INFO 26734 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Finish http call to Airline Client
        2019-06-05 15:21:26.615  INFO 26734 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Finish http call to Hotel Client
     */
    @Test
    public void testHotObservableCall() throws IOException {
        long startTime = System.currentTimeMillis();
        myTripController.getFromHotObservable(123);
        long endTime = System.currentTimeMillis();

        Assert.assertTrue(endTime - startTime > DEFAULT_SLEEP_TIME_IN_MILLIS);
        Assert.assertTrue(endTime - startTime < DEFAULT_SLEEP_TIME_IN_MILLIS * 2);
    }

    /*
        2019-06-05 15:22:47.438  INFO 26752 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Start http call to Hotel Client
        2019-06-05 15:22:47.438  INFO 26752 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Start http call to Airline Client
        2019-06-05 15:22:49.441  INFO 26752 --- [hystrix-Hotel-1] .x.f.h.a.AsyncTestHotelHttpClient : Finish http call to Hotel Client
        2019-06-05 15:22:49.441  INFO 26752 --- [strix-Airline-1] .f.h.a.AsyncTestAirlineHttpClient : Finish http call to Airline Client
     */
    @Test
    public void testColdObservableCall() throws IOException {
        long startTime = System.currentTimeMillis();
        myTripController.getFromHotObservable(123);
        long endTime = System.currentTimeMillis();

        Assert.assertTrue(endTime - startTime > DEFAULT_SLEEP_TIME_IN_MILLIS);
        Assert.assertTrue(endTime - startTime < DEFAULT_SLEEP_TIME_IN_MILLIS * 2);
    }
}
