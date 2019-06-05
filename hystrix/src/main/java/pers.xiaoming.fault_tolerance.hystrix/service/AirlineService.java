package pers.xiaoming.fault_tolerance.hystrix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.backends.HttpClient;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.hystrix.hystrix.HystrixCommandFactory;

import java.io.IOException;

@Service
public class AirlineService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private HttpClient client;
    private HystrixCommandFactory hystrixCommandFactory;

    @Autowired
    public AirlineService(
            @Qualifier("airlineClient") HttpClient client,
            @Qualifier("airlineHystrixCommandFactory") HystrixCommandFactory hystrixCommandFactory) {
        this.client = client;
        this.hystrixCommandFactory = hystrixCommandFactory;
    }

    public AirlineInfo getInfo(long id) throws IOException {
        String infoStr = hystrixCommandFactory.createCommand(client, id).execute();
        return OBJECT_MAPPER.readValue(infoStr, AirlineInfo.class);
    }
}
