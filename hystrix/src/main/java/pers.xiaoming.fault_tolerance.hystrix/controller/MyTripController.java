package pers.xiaoming.fault_tolerance.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaoming.fault_tolerance.hystrix.service.AirlineService;
import pers.xiaoming.fault_tolerance.hystrix.service.HotelService;

@RestController
@RequestMapping("/web_client")
public class MyTripController {

    private AirlineService airlineService;
    private HotelService hotelService;

    @Autowired
    public MyTripController(AirlineService airlineService, HotelService hotelService) {
        this.airlineService = airlineService;
        this.hotelService = hotelService;
    }

    public String get(@RequestParam("trip_id") long id) {
        String hotelInfo = hotelService.getInfo(id);
        String airlineInfo = airlineService.getInfo(id);

        return hotelInfo + " " + airlineInfo;
    }
}
