package pers.xiaoming.fault_tolerance.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;

@Service
public class AirlineService {
    private HttpClient client;
    private HystrixCommandFactory hystrixCommandFactory;

    @Autowired
    public AirlineService(
            @Qualifier("airlineClient") HttpClient client,
            @Qualifier("airlineHystrixCommandFactory") HystrixCommandFactory hystrixCommandFactory) {
        this.client = client;
        this.hystrixCommandFactory = hystrixCommandFactory;
    }

    public String getInfo(long id) {
        return hystrixCommandFactory.createCommand(client, id).execute();
    }
}