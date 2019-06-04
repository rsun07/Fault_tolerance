package pers.xiaoming.fault_tolerance.hystrix.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web_client")
public class MyTripController {

    public String get() {
        return null;
    }
}
