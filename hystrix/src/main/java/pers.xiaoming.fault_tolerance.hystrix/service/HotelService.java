package pers.xiaoming.fault_tolerance.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;

@Service
public class HotelService {
    private HttpClient client;
    private HystrixCommandFactory hystrixCommandFactory;

    @Autowired
    public HotelService(
            @Qualifier("hotelClient") HttpClient client,
            @Qualifier("hotelHystrixCommandFactory") HystrixCommandFactory hystrixCommandFactory) {
        this.client = client;
        this.hystrixCommandFactory = hystrixCommandFactory;
    }

    public String getInfo(long id) {
        return hystrixCommandFactory.createCommand(client, id).execute();
    }
}
