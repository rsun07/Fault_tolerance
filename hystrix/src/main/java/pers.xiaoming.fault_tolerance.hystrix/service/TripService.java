package pers.xiaoming.fault_tolerance.hystrix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xiaoming.fault_tolerance.common.entity.AirlineInfo;
import pers.xiaoming.fault_tolerance.common.entity.HotelInfo;
import pers.xiaoming.fault_tolerance.common.entity.TripInfo;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class TripService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final HotelService hotelService;
    private final AirlineService airlineService;

    @Autowired
    public TripService(HotelService hotelService, AirlineService airlineService) {
        this.hotelService = hotelService;
        this.airlineService = airlineService;
    }

    public TripInfo get(long id) throws IOException {
        String hotelInfoStr = hotelService.get(id);
        String airlineInfoStr = airlineService.get(id);

        return createTripInfo(id, hotelInfoStr, airlineInfoStr);
    }

    public TripInfo getAsync(long id) throws ExecutionException, InterruptedException, IOException {
        Future<String> hotelInfoFuture = hotelService.getAsync(id);
        Future<String> airlineInfoFuture = airlineService.getAsync(id);

        return createTripInfo(id, hotelInfoFuture.get(), airlineInfoFuture.get());
    }

    private TripInfo createTripInfo(long id, String hotelInfoStr, String airlineInfoStr) throws IOException {
        HotelInfo hotelInfo = OBJECT_MAPPER.readValue(hotelInfoStr, HotelInfo.class);
        AirlineInfo airlineInfo = OBJECT_MAPPER.readValue(airlineInfoStr, AirlineInfo.class);

        return TripInfo.builder()
                .id(id)
                .hotelInfo(hotelInfo)
                .airlineInfo(airlineInfo)
                .build();
    }
}
