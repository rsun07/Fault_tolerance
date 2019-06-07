package pers.xiaoming.fault_tolerance.resilience4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.resilience4j.service.TripService;

@RestController
@RequestMapping("/my_trip")
public class MyTripController {

    private TripService service;

    @Autowired
    public MyTripController(TripService service) {
        this.service = service;
    }

    @GetMapping
    public TripInfo get(@RequestParam("trip_id") long id) throws Exception {
        return service.get(id);
    }
}
