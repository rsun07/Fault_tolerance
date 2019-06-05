package pers.xiaoming.fault_tolerance.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;
import pers.xiaoming.fault_tolerance.hystrix.service.TripService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/web-client")
public class MyTripController {

    private TripService service;

    @Autowired
    public MyTripController(TripService service) {
        this.service = service;
    }

    @GetMapping
    public TripInfo get(@RequestParam("trip_id") long id) throws IOException {
        return service.get(id);
    }

    @GetMapping("/get_async")
    public TripInfo getAsync(@RequestParam("trip_id") long id) throws IOException, ExecutionException, InterruptedException {
        return service.getAsync(id);
    }
}
