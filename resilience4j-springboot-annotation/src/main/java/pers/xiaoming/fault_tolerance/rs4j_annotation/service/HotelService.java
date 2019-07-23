package pers.xiaoming.fault_tolerance.rs4j_annotation.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;

@Service
public class HotelService {
    private HttpClient client;

    @Autowired
    public HotelService(
            @Qualifier("hotelClient") HttpClient client) {
        this.client = client;
    }

    @CircuitBreaker(name = "hotel")
    public String get(long id) throws IOException {
        return client.get(id);
    }
}
