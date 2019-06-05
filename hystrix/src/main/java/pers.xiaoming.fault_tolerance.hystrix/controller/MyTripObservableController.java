package pers.xiaoming.fault_tolerance.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.service.TripObservableService;

import java.io.IOException;

@RestController
@RequestMapping("/web-client-observable")
public class MyTripObservableController {
    private TripObservableService service;

    @Autowired
    public MyTripObservableController(TripObservableService service) {
        this.service = service;
    }

    @GetMapping("/get_hot_observable")
    public TripInfo getFromHotObservable(@RequestParam("trip_id") long id) throws IOException {
        return service.getFromHotObservable(id);
    }

    @GetMapping("/get_cold_observable")
    public TripInfo getFromColdObservable(@RequestParam("trip_id") long id) throws IOException {
        return service.getFromColdObservable(id);
    }
}
