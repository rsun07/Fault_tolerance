package pers.xiaoming.fault_tolerance.rs4j_annotation.fallback_test;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.test.constant.TestConstants;
import pers.xiaoming.fault_tolerance.rs4j_annotation.service.HotelService;

import java.io.IOException;

@Service
@Slf4j
public class Rs4jTestHotelService extends HotelService {
    private HttpClient client;

    @Autowired
    public Rs4jTestHotelService(
            @Qualifier("hotelClient") HttpClient client) {
        super(client);
    }

    @Override
    @CircuitBreaker(name = "hotel", fallbackMethod = "fallback")
    public String get(long id) throws IOException {
        return client.get(id);
    }

    private String fallback(long id, Throwable t) {
        log.error("Fall back method triggered when query id {}, Error: {}", id, t.getMessage());
        return TestConstants.FALLBACK_HOTEL_INFO_STRING;
    }
}
