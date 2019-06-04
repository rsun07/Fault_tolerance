package pers.xiaoming.fault_tolerance.hystrix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.hystrix.backends.HttpClient;
import pers.xiaoming.fault_tolerance.hystrix.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;

import java.io.IOException;

@Service
public class HotelService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private HttpClient client;
    private HystrixCommandFactory hystrixCommandFactory;

    @Autowired
    public HotelService(
            @Qualifier("hotelClient") HttpClient client,
            @Qualifier("hotelHystrixCommandFactory") HystrixCommandFactory hystrixCommandFactory) {
        this.client = client;
        this.hystrixCommandFactory = hystrixCommandFactory;
    }

    public HotelInfo getInfo(long id) throws IOException {
        String infoStr = hystrixCommandFactory.createCommand(client, id).execute();
        return OBJECT_MAPPER.readValue(infoStr, HotelInfo.class);
    }
}
