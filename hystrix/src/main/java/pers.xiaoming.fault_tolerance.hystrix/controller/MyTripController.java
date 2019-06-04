package pers.xiaoming.fault_tolerance.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaoming.fault_tolerance.hystrix.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.hystrix.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.hystrix.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.service.AirlineService;
import pers.xiaoming.fault_tolerance.hystrix.service.HotelService;

import java.io.IOException;

@RestController
@RequestMapping("/web-client")
public class MyTripController {

    private AirlineService airlineService;
    private HotelService hotelService;

    @Autowired
    public MyTripController(AirlineService airlineService, HotelService hotelService) {
        this.airlineService = airlineService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public TripInfo get(@RequestParam("trip_id") long id) throws IOException {
        HotelInfo hotelInfo = hotelService.getInfo(id);
        AirlineInfo airlineInfo = airlineService.getInfo(id);

        TripInfo tripInfo = new TripInfo(id);
        tripInfo.setHotelInfo(hotelInfo);
        tripInfo.setAirlineInfo(airlineInfo);

        return tripInfo;
    }
}
