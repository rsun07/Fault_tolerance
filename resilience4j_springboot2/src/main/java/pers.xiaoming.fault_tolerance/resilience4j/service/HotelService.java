package pers.xiaoming.fault_tolerance.resilience4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.resilience4j.rs4j.Rs4jCommandFactory;

@Service
public class HotelService {
    private HttpClient client;
    private Rs4jCommandFactory<String> rs4jCommandFactory;

    @Autowired
    public HotelService(
            @Qualifier("hotelClient") HttpClient client,
            @Qualifier("hotelRs4jCommandFactory") Rs4jCommandFactory<String> rs4jCommandFactory) {
        this.client = client;
        this.rs4jCommandFactory = rs4jCommandFactory;
    }

    public String get(long id) {
        return rs4jCommandFactory.execute(() -> client.get(id));
    }
}
