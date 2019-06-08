package pers.xiaoming.fault_tolerance.rs4j_annotation.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;

import java.io.IOException;

@Service
public class AirlineService {
    private HttpClient client;

    @Autowired
    public AirlineService(
            @Qualifier("airlineClient") HttpClient client) {
        this.client = client;
    }

    @CircuitBreaker(name = "airline_backend")
    public String get(long id) throws IOException {
        return client.get(id);
    }
}
